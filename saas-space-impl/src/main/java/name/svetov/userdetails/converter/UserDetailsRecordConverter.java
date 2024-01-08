package name.svetov.userdetails.converter;

import name.svetov.config.SaasMapperDefaultConfig;
import name.svetov.userdetails.model.UserDetails;
import org.jooq.Record;
import org.jooq.RecordMapper;
import org.jooq.generated.tables.records.UserDetailsRecord;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
    config = SaasMapperDefaultConfig.class,
    builder = @Builder(disableBuilder = true),
    uses = UserDetailsRecordAfterConverter.class
)
public interface UserDetailsRecordConverter extends RecordMapper<Record, UserDetails> {
    @Override
    default UserDetails map(Record rec) {
        return rec == null ? null : convert(rec, rec.into(UserDetailsRecord.class));
    }

    @Mapping(target = "password", ignore = true)
    UserDetails convert(Record rec, UserDetailsRecord record);

}
