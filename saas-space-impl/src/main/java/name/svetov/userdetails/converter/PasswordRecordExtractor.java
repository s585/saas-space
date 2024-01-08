package name.svetov.userdetails.converter;

import jakarta.inject.Singleton;
import name.svetov.password.model.Password;
import org.jooq.Record;

import java.time.OffsetDateTime;

import static name.svetov.constants.TableConstants.*;

@Singleton
public class PasswordRecordExtractor {
    public Password extractPassword(Record rec) {
        return Password.builder()
            .secret(rec.get(PASSWORD_SECRET_FIELD_ALIAS, String.class))
            .createdDate(rec.get(PASSWORD_CREATED_DATE_FIELD_ALIAS, OffsetDateTime.class))
            .createdDate(rec.get(PASSWORD_UPDATED_DATE_FIELD_ALIAS, OffsetDateTime.class))
            .build();
    }
}
