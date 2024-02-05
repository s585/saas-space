package name.svetov.password.repository;

import io.micronaut.context.annotation.Requires;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import name.svetov.password.model.Password;
import org.jooq.DSLContext;
import org.jooq.InsertSetMoreStep;
import org.jooq.generated.tables.records.PasswordRecord;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;

import static org.jooq.generated.Tables.PASSWORD;

@Singleton
@Requires(property = "micronaut.application.type", value = "reactive")
public class PasswordReactiveRepositoryImpl implements PasswordReactiveRepository {
    private final DSLContext dslContext;

    public PasswordReactiveRepositoryImpl(@Named("r2dbc") DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Mono<Boolean> add(Password password) {
        return Mono.from(insert(password))
            .map(response -> response == 1);
    }

    InsertSetMoreStep<PasswordRecord> insert(Password password) {
        var now = OffsetDateTime.now();
        return dslContext.insertInto(PASSWORD)
            .set(PASSWORD.ID, password.getId())
            .set(PASSWORD.USER_DETAILS_ID, password.getUserDetailsId())
            .set(PASSWORD.SECRET, password.getSecret())
            .set(PASSWORD.CREATED_DATE, now)
            .set(PASSWORD.UPDATED_DATE, now);
    }
}
