package name.svetov.userrelationships.repository;

import io.micronaut.context.annotation.Requires;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import name.svetov.userrelationships.constants.UserRelationshipsEnum;
import org.jooq.DSLContext;
import org.jooq.InsertSetMoreStep;
import org.jooq.generated.tables.records.UserRelationshipsRecord;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;
import java.util.UUID;

import static org.jooq.generated.Tables.USER_RELATIONSHIPS;

@Singleton
public class UserRelationshipsRepositoryImpl implements UserRelationshipsRepository {
    private final DSLContext context;

    public UserRelationshipsRepositoryImpl(@Named("r2dbc") DSLContext context) {
        this.context = context;
    }

    @Override
    public Publisher<UUID> findAllByUserIdAndType(UUID userId, UserRelationshipsEnum relationship) {
        return Flux.from(
                context.select(USER_RELATIONSHIPS.USER_ID_RIGHT)
                    .from(USER_RELATIONSHIPS)
                    .where(
                        USER_RELATIONSHIPS.USER_ID_LEFT.eq(userId),
                        USER_RELATIONSHIPS.TYPE.eq(relationship.getType())
                    )
            )
            .map(response -> response.into(UUID.class));
    }

    @Override
    public Publisher<Boolean> add(UUID userIdLeft,
                             UUID userIdRight,
                             UserRelationshipsEnum relationship) {
        return Mono.from(insert(userIdLeft, userIdRight, UserRelationshipsEnum.FRIENDSHIP))
            .map(response -> response == 1);
    }

    private InsertSetMoreStep<UserRelationshipsRecord> insert(UUID userIdLeft,
                                                              UUID userIdRight,
                                                              UserRelationshipsEnum relationship) {
        var now = OffsetDateTime.now();
        return context.insertInto(USER_RELATIONSHIPS)
            .set(USER_RELATIONSHIPS.USER_ID_LEFT, userIdLeft)
            .set(USER_RELATIONSHIPS.USER_ID_RIGHT, userIdRight)
            .set(USER_RELATIONSHIPS.TYPE, relationship.getType())
            .set(USER_RELATIONSHIPS.CREATED_DATE, now)
            .set(USER_RELATIONSHIPS.UPDATED_DATE, now);
    }

    @Override
    public Publisher<Boolean> delete(UUID userIdLeft,
                                UUID userIdRight,
                                UserRelationshipsEnum relationship) {
        return Mono.from(
                context.deleteFrom(USER_RELATIONSHIPS)
                    .where(
                        USER_RELATIONSHIPS.USER_ID_LEFT.eq(userIdLeft),
                        USER_RELATIONSHIPS.USER_ID_RIGHT.eq(userIdRight),
                        USER_RELATIONSHIPS.TYPE.eq(relationship.getType())
                    )
            )
            .map(response -> response == 1);
    }
}
