package name.svetov.post.converter;

import name.svetov.config.SaasMapperDefaultConfig;
import name.svetov.post.model.Post;
import name.svetov.userdetails.converter.UserDetailsRecordAfterConverter;
import org.apache.commons.collections4.CollectionUtils;
import org.jooq.Record;
import org.jooq.RecordMapper;
import org.jooq.generated.tables.records.PostRecord;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(
    config = SaasMapperDefaultConfig.class,
    builder = @Builder(disableBuilder = true),
    uses = UserDetailsRecordAfterConverter.class
)
public interface PostRecordConverter extends RecordMapper<Record, Post> {
    @Override
    default Post map(Record rec) {
        return rec == null ? null : convert(rec, rec.into(PostRecord.class));
    }

    default List<Post> map(Collection<Record> recs) {
        return CollectionUtils.emptyIfNull(recs).stream()
            .map(rec -> this.convert(rec, rec.into(PostRecord.class)))
            .toList();
    }

    Post convert(Record rec, PostRecord record);
}
