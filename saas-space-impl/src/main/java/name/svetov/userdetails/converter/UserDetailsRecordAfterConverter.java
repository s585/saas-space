package name.svetov.userdetails.converter;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import name.svetov.userdetails.model.UserDetails;
import org.jooq.Record;
import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;

@Singleton
@RequiredArgsConstructor
public class UserDetailsRecordAfterConverter {
    private final PasswordRecordExtractor passwordRecordExtractor;

    @BeforeMapping
    public void before(@MappingTarget UserDetails userDetails, Record rec) {
        userDetails.setPassword(passwordRecordExtractor.extractPassword(rec));
    }
}
