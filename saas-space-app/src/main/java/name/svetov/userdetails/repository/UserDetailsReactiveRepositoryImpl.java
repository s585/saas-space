package name.svetov.userdetails.repository;

import io.micronaut.context.annotation.Requires;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import name.svetov.userdetails.converter.UserDetailsRecordConverter;
import name.svetov.userdetails.model.SearchUserCmd;
import name.svetov.userdetails.model.UserDetails;
import org.apache.commons.collections4.CollectionUtils;
import org.jooq.Record;
import org.jooq.*;
import org.jooq.generated.tables.records.UserDetailsRecord;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import static name.svetov.constants.TableConstants.*;
import static org.jooq.generated.Tables.PASSWORD;
import static org.jooq.generated.Tables.USER_DETAILS;

@Singleton
@Requires(property = "micronaut.application.type", value = "reactive")
public class UserDetailsReactiveRepositoryImpl implements UserDetailsReactiveRepository {
    private final DSLContext context;
    private final UserDetailsRecordConverter recordConverter;

    public UserDetailsReactiveRepositoryImpl(@Named("r2dbc") DSLContext context, UserDetailsRecordConverter recordConverter) {
        this.context = context;
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
    public Mono<Boolean> existsByUsername(String username) {
        return getOneByUsername(username)
            .map(Objects::nonNull)
            .switchIfEmpty(Mono.just(false));
    }

    @Override
    public Mono<Boolean> add(UserDetails userDetails) {
        return Mono.from(insert(userDetails))
            .map(response -> response == 1);
    }

    @Override
    public Flux<UserDetails> search(SearchUserCmd cmd) {
        return Flux.from(
                select()
                    .where(
                        USER_DETAILS.FIRST_NAME.like(cmd.getFirstName() + "%"),
                        USER_DETAILS.LAST_NAME.like(cmd.getLastName() + "%")
                    )
                    .orderBy(USER_DETAILS.ID)
            )
            .map(recordConverter);
    }

    InsertSetMoreStep<UserDetailsRecord> insert(UserDetails userDetails) {
        var now = OffsetDateTime.now();
        return context.insertInto(USER_DETAILS)
            .set(USER_DETAILS.ID, userDetails.getId())
            .set(USER_DETAILS.USERNAME, userDetails.getUsername())
            .set(USER_DETAILS.PASSWORD, userDetails.getPassword().getId())
            .set(USER_DETAILS.FIRST_NAME, userDetails.getFirstName())
            .set(USER_DETAILS.LAST_NAME, userDetails.getLastName())
            .set(USER_DETAILS.BIRTH_DATE, userDetails.getBirthDate())
            .set(USER_DETAILS.SEX, userDetails.getSex())
            .set(USER_DETAILS.CITY, userDetails.getCity())
            .set(USER_DETAILS.COUNTRY, userDetails.getCountry())
            .set(USER_DETAILS.HOBBIES, CollectionUtils.emptyIfNull(userDetails.getHobbies()).toArray(String[]::new))
            .set(USER_DETAILS.CREATED_DATE, now)
            .set(USER_DETAILS.UPDATED_DATE, now);
    }

    private SelectOnConditionStep<Record> select() {
        return context.select(this.getUserDetailsFields())
            .from(USER_DETAILS)
            .innerJoin(PASSWORD)
            .on(USER_DETAILS.PASSWORD.eq(PASSWORD.ID));
    }

    private Set<Field<?>> getUserDetailsFields() {
        return Set.of(
            USER_DETAILS.ID,
            USER_DETAILS.USERNAME,
            USER_DETAILS.FIRST_NAME,
            USER_DETAILS.LAST_NAME,
            USER_DETAILS.BIRTH_DATE,
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

