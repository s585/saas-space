package name.svetov.userrelationships.adapter;

import org.reactivestreams.Publisher;

import java.util.UUID;

public interface UserRelationshipsWebAdapter {
    Publisher<Boolean> addFriend(UUID friendId);

    Publisher<Boolean> deleteFriend(UUID friendId);
}
