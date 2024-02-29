package name.svetov.userrelationships.controller;

import io.micronaut.http.annotation.Controller;
import lombok.RequiredArgsConstructor;
import name.svetov.userrelationships.adapter.UserRelationshipsWebAdapter;
import org.reactivestreams.Publisher;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class UserRelationshipsControllerImpl implements UserRelationshipsController {
    private final UserRelationshipsWebAdapter webAdapter;
    @Override
    public Publisher<Boolean> addFriend(UUID friendId) {
        return webAdapter.addFriend(friendId);
    }

    @Override
    public Publisher<Boolean> deleteFriend(UUID friendId) {
        return webAdapter.deleteFriend(friendId);
    }
}
