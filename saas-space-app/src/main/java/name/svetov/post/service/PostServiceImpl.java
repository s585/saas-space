package name.svetov.post.service;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import name.svetov.post.model.Post;
import name.svetov.post.rabbitmq.PostEventPublisher;
import name.svetov.post.rabbitmq.event.PostCreatedEvent;
import name.svetov.post.rabbitmq.event.PostDeletedEvent;
import name.svetov.post.rabbitmq.event.PostUpdatedEvent;
import name.svetov.post.repository.PostRepository;
import name.svetov.userdetails.service.CurrentUserService;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Singleton
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final CurrentUserService currentUserService;
    private final PostRepository postRepository;
    private final PostEventPublisher postEventPublisher;

    @Override
    public Publisher<Post> getOne(UUID postId) {
        return postRepository.getOne(postId);
    }

    @Override
    public Publisher<Post> create(Post post) {
        var postId = UUID.randomUUID();
        return Mono.from(currentUserService.getCurrentUserId())
            .flatMap(
                currentUserId -> Mono.from(
                    postRepository.add(
                        post.toBuilder()
                            .id(postId)
                            .authorId(currentUserId)
                            .build()
                    )
                )
            )
            .flatMap(created -> Mono.from(postEventPublisher.publish(buildPostCreatedEvent(created)))
                .then(Mono.just(created))
            );
    }

    private PostCreatedEvent buildPostCreatedEvent(Post post) {
        return PostCreatedEvent.builder()
            .post(post)
            .build();
    }

    @Override
    public Publisher<Post> update(UUID postId, Post post) {
        return Mono.from(postRepository.getOne(postId))
            .flatMap(persisted ->
                Mono.from(postRepository.update(postId, post))
                    .flatMap(updated -> Mono.from(postEventPublisher.publish(buildPostUpdatedEvent(persisted, updated)))
                        .then(Mono.just(updated)))
            );
    }

    private PostUpdatedEvent buildPostUpdatedEvent(Post previousState, Post actualState) {
        return PostUpdatedEvent.builder()
            .previousState(previousState)
            .actualState(actualState)
            .build();
    }

    @Override
    public Publisher<UUID> delete(UUID postId) {
        return Mono.from(postRepository.delete(postId))
            .flatMap(deleted -> Mono.from(postEventPublisher.publish(buildPostDeletedEvent(deleted)))
                .then(Mono.just(deleted.getId())));
    }

    private PostDeletedEvent buildPostDeletedEvent(Post post) {
        return PostDeletedEvent.builder()
            .post(post)
            .build();
    }
}
