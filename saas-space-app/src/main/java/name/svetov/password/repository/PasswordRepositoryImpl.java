package name.svetov.password.repository;

import jakarta.inject.Named;
import jakarta.inject.Singleton;
import name.svetov.password.model.Password;
import org.jooq.DSLContext;
import org.jooq.InsertSetMoreStep;
import org.jooq.generated.tables.records.PasswordRecord;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;

import static org.jooq.generated.Tables.PASSWORD;

@Singleton
public class PasswordRepositoryImpl implements PasswordRepository {
    private final DSLContext context;

    public PasswordRepositoryImpl(@Named("r2dbc") DSLContext context) {
        this.context = context;
    }

    @Override
    public Publisher<Boolean> add(Password password) {
        return Mono.from(insert(password))
            .map(response -> response == 1);
    }

    private InsertSetMoreStep<PasswordRecord> insert(Password password) {
        var now = OffsetDateTime.now();
        return context.insertInto(PASSWORD)
            .set(PASSWORD.ID, password.getId())
            .set(PASSWORD.USER_DETAILS_ID, password.getUserDetailsId())
            .set(PASSWORD.SECRET, password.getSecret())
            .set(PASSWORD.CREATED_DATE, now)
            .set(PASSWORD.UPDATED_DATE, now);
    }
}
