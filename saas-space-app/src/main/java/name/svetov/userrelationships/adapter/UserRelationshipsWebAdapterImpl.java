package name.svetov.userrelationships.adapter;

import io.micronaut.context.annotation.Requires;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import name.svetov.userrelationships.service.UserRelationshipsService;
import org.reactivestreams.Publisher;

import java.util.UUID;

@Singleton
@RequiredArgsConstructor
public class UserRelationshipsWebAdapterImpl implements UserRelationshipsWebAdapter {
    private final UserRelationshipsService userRelationshipsService;

    @Override
    public Publisher<Boolean> addFriend(UUID friendId) {
        return userRelationshipsService.addFriend(friendId);
    }

    @Override
    public Publisher<Boolean> deleteFriend(UUID friendId) {
        return userRelationshipsService.deleteFriend(friendId);
    }
}
