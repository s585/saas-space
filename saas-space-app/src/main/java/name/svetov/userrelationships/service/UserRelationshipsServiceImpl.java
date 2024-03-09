package name.svetov.userrelationships.service;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import name.svetov.userdetails.service.CurrentUserService;
import name.svetov.userrelationships.constants.UserRelationshipsEnum;
import name.svetov.userrelationships.repository.UserRelationshipsRepository;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Singleton
@RequiredArgsConstructor
public class UserRelationshipsServiceImpl implements UserRelationshipsService {
    private final CurrentUserService currentUserService;
    private final UserRelationshipsRepository userRelationshipsRepository;

    @Override
    public Publisher<UUID> findAllFriendsByUserId(UUID userId) {
        return Flux.from(
            userRelationshipsRepository.findAllByUserIdAndType(
                userId,
                UserRelationshipsEnum.FRIENDSHIP
            )
        );
    }

    @Override
    public Publisher<Boolean> addFriend(UUID userId) {
        return Mono.from(currentUserService.getCurrentUser())
            .flatMap(currentUser ->
                Mono.from(userRelationshipsRepository.add(currentUser.getId(), userId, UserRelationshipsEnum.FRIENDSHIP))
            );
    }

    @Override
    public Publisher<Boolean> deleteFriend(UUID userId) {
        return Mono.from(currentUserService.getCurrentUser())
            .flatMap(currentUser ->
                Mono.from(userRelationshipsRepository.delete(currentUser.getId(), userId, UserRelationshipsEnum.FRIENDSHIP))
            );
    }
}
