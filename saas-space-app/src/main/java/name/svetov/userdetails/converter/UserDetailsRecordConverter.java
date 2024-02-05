package name.svetov.userdetails.converter;

import name.svetov.config.SaasMapperDefaultConfig;
import name.svetov.userdetails.model.UserDetails;
import org.apache.commons.collections4.CollectionUtils;
import org.jooq.Record;
import org.jooq.RecordMapper;
import org.jooq.generated.tables.records.UserDetailsRecord;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

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

    default List<UserDetails> map(Collection<Record> recs) {
        return CollectionUtils.emptyIfNull(recs).stream()
            .map(rec -> this.convert(rec, rec.into(UserDetailsRecord.class)))
            .toList();
    }

    @Mapping(target = "password", ignore = true)
    UserDetails convert(Record rec, UserDetailsRecord record);

}
