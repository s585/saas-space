package name.svetov.post.repository;

import jakarta.inject.Named;
import jakarta.inject.Singleton;
import name.svetov.post.converter.PostRecordConverter;
import name.svetov.post.model.Post;
import org.jooq.DSLContext;
import org.jooq.InsertSetMoreStep;
import org.jooq.SelectConditionStep;
import org.jooq.UpdateSetMoreStep;
import org.jooq.generated.tables.records.PostRecord;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;
import java.util.UUID;

import static org.jooq.generated.Tables.POST;

@Singleton
public class PostRepositoryImpl implements PostRepository {
    private final DSLContext context;
    private final PostRecordConverter converter;

    public PostRepositoryImpl(@Named("r2dbc") DSLContext context, PostRecordConverter converter) {
        this.context = context;
        this.converter = converter;
    }

    @Override
    public Publisher<Post> getOne(UUID postId) {
        return Mono.from(select(postId))
            .mapNotNull(converter::map);
    }

    @Override
    public Publisher<Post> add(Post post) {
        return Mono.from(insert(post).returning())
            .map(response -> response.into(Post.class));
    }

    @Override
    public Publisher<Post> update(UUID postId, Post post) {
        return Mono.from(
                update(post)
                    .where(POST.ID.eq(postId))
                    .returning()
            )
            .map(response -> response.into(Post.class));
    }

    @Override
    public Publisher<Post> delete(UUID postId) {
        return Mono.from(
            context.deleteFrom(POST)
                .where(POST.ID.eq(postId))
                .returning()
        ).map(response -> response.into(Post.class));
    }

    private SelectConditionStep<PostRecord> select(UUID postId) {
        return context.selectFrom(POST)
            .where(POST.ID.eq(postId));
    }

    private InsertSetMoreStep<PostRecord> insert(Post post) {
        var now = OffsetDateTime.now();
        return context.insertInto(POST)
            .set(POST.AUTHOR_ID, post.getAuthorId())
            .set(POST.BODY, post.getBody())
            .set(POST.CREATED_DATE, now)
            .set(POST.UPDATED_DATE, now);
    }

    private UpdateSetMoreStep<PostRecord> update(Post post) {
        return context.update(POST)
            .set(POST.BODY, post.getBody())
            .set(POST.UPDATED_DATE, OffsetDateTime.now());
    }
}
