package name.svetov.userdetails.repository;

import jakarta.inject.Named;
import jakarta.inject.Singleton;
import name.svetov.userdetails.converter.UserDetailsRecordConverter;
import name.svetov.userdetails.model.UserDetails;
import org.apache.commons.collections4.CollectionUtils;
import org.jooq.Record;
import org.jooq.*;
import org.jooq.generated.tables.records.UserDetailsRecord;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

import static name.svetov.constants.TableConstants.*;
import static org.jooq.generated.Tables.PASSWORD;
import static org.jooq.generated.Tables.USER_DETAILS;

@Singleton
public class UserDetailsRepositoryImpl implements UserDetailsRepository {
    private final DSLContext dslContext;
    private final UserDetailsRecordConverter recordConverter;

    public UserDetailsRepositoryImpl(@Named("r2dbc") DSLContext dslContext, UserDetailsRecordConverter recordConverter) {
        this.dslContext = dslContext;
        this.recordConverter = recordConverter;
    }

    @Override
    public Mono<UserDetails> getOneById(UUID userDetailsId) {
        return Mono.from(
                select()
                    .where(USER_DETAILS.ID.eq(userDetailsId))
            )
            .map(recordConverter);
    }

    @Override
    public Mono<UserDetails> getOneByUsername(String username) {
        return Mono.from(
                select()
                    .where(USER_DETAILS.USERNAME.eq(username))
            )
            .map(recordConverter);
    }

    @Override
    public Mono<Boolean> add(UserDetails userDetails) {
        return Mono.from(generateInsertQuery(userDetails))
            .map(response -> response == 1);
    }

    InsertSetMoreStep<UserDetailsRecord> generateInsertQuery(UserDetails userDetails) {
        var now = ZonedDateTime.now();
        return dslContext.insertInto(USER_DETAILS)
            .set(USER_DETAILS.ID, userDetails.getId())
            .set(USER_DETAILS.USERNAME, userDetails.getUsername())
            .set(USER_DETAILS.SECRET, userDetails.getPassword().getId())
            .set(USER_DETAILS.FIRST_NAME, userDetails.getFirstName())
            .set(USER_DETAILS.LAST_NAME, userDetails.getLastName())
            .set(USER_DETAILS.AGE, (int) userDetails.getAge())
            .set(USER_DETAILS.SEX, userDetails.getSex())
            .set(USER_DETAILS.CITY, userDetails.getCity())
            .set(USER_DETAILS.COUNTRY, userDetails.getCountry())
            .set(USER_DETAILS.HOBBIES, CollectionUtils.emptyIfNull(userDetails.getHobbies()).toArray(String[]::new))
            .set(USER_DETAILS.CREATED_DATE, now.toOffsetDateTime())
            .set(USER_DETAILS.UPDATED_DATE, now.toOffsetDateTime());
    }

    private SelectOnConditionStep<Record> select() {
        return dslContext.select(this.getUserDetailsFields())
            .from(USER_DETAILS)
            .innerJoin(PASSWORD)
            .on(USER_DETAILS.SECRET.eq(PASSWORD.ID));
    }

    private Set<Field<?>> getUserDetailsFields() {
        return Set.of(
            USER_DETAILS.ID,
            USER_DETAILS.USERNAME,
            USER_DETAILS.FIRST_NAME,
            USER_DETAILS.LAST_NAME,
            USER_DETAILS.AGE,
            USER_DETAILS.SEX,
            USER_DETAILS.CITY,
            USER_DETAILS.COUNTRY,
            USER_DETAILS.HOBBIES,
            USER_DETAILS.CREATED_DATE,
            USER_DETAILS.UPDATED_DATE,
            PASSWORD.SECRET.as(PASSWORD_SECRET_FIELD_ALIAS),
            PASSWORD.CREATED_DATE.as(PASSWORD_CREATED_DATE_FIELD_ALIAS),
            PASSWORD.UPDATED_DATE.as(PASSWORD_UPDATED_DATE_FIELD_ALIAS)
        );
    }
}
