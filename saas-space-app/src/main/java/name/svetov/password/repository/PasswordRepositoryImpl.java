package name.svetov.password.repository;

import io.micronaut.context.annotation.Requires;
import jakarta.inject.Singleton;
import name.svetov.password.model.Password;
import org.jooq.DSLContext;
import org.jooq.InsertSetMoreStep;
import org.jooq.generated.tables.records.PasswordRecord;

import java.time.OffsetDateTime;

import static org.jooq.generated.Tables.PASSWORD;

@Singleton
@Requires(property = "micronaut.application.type", value = "blocking")
public class PasswordRepositoryImpl implements PasswordRepository {
    private final DSLContext context;

    public PasswordRepositoryImpl(DSLContext context) {
        this.context = context;
    }

    @Override
    public boolean add(Password password) {
        return insert(password)
            .execute() == 1;
    }

    InsertSetMoreStep<PasswordRecord> insert(Password password) {
        var now = OffsetDateTime.now();
        return context.insertInto(PASSWORD)
            .set(PASSWORD.ID, password.getId())
            .set(PASSWORD.USER_DETAILS_ID, password.getUserDetailsId())
            .set(PASSWORD.SECRET, password.getSecret())
            .set(PASSWORD.CREATED_DATE, now)
            .set(PASSWORD.UPDATED_DATE, now);
    }
}
