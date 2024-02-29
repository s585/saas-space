package name.svetov.userrelationships.service;

import org.reactivestreams.Publisher;

import java.util.UUID;

public interface UserRelationshipsService {
    Publisher<UUID> findAllFriendsByUserId(UUID userId);

    Publisher<Boolean> addFriend(UUID friendId);

    Publisher<Boolean> deleteFriend(UUID friendId);
}
