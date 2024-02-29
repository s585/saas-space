package name.svetov.userrelationships.repository;

import name.svetov.userrelationships.constants.UserRelationshipsEnum;
import org.reactivestreams.Publisher;

import java.util.UUID;

public interface UserRelationshipsRepository {
    Publisher<UUID> findAllByUserIdAndType(UUID userId, UserRelationshipsEnum relationship);

    Publisher<Boolean> add(UUID userIdLeft,
                      UUID userIdRight,
                      UserRelationshipsEnum relationship);

    Publisher<Boolean> delete(UUID userIdLeft,
                         UUID userIdRight,
                         UserRelationshipsEnum relationship);
}
