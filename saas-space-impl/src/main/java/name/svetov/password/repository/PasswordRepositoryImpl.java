package name.svetov.password.repository;

import jakarta.inject.Named;
import jakarta.inject.Singleton;
import name.svetov.password.model.Password;
import org.jooq.DSLContext;
import org.jooq.InsertSetMoreStep;
import org.jooq.generated.tables.records.PasswordRecord;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;

import static org.jooq.generated.Tables.PASSWORD;

@Singleton
public class PasswordRepositoryImpl implements PasswordRepository {
    private final DSLContext dslContext;

    public PasswordRepositoryImpl(@Named("r2dbc") DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Mono<Boolean> add(Password password) {
        return Mono.from(generateInsertQuery(password))
            .map(response -> response == 1);
    }

    InsertSetMoreStep<PasswordRecord> generateInsertQuery(Password password) {
        var now = ZonedDateTime.now();
        return dslContext.insertInto(PASSWORD)
            .set(PASSWORD.ID, password.getId())
            .set(PASSWORD.USER_DETAILS_ID, password.getUserDetailsId())
            .set(PASSWORD.SECRET, password.getSecret())
            .set(PASSWORD.CREATED_DATE, now.toOffsetDateTime())
            .set(PASSWORD.UPDATED_DATE, now.toOffsetDateTime());
    }
}
