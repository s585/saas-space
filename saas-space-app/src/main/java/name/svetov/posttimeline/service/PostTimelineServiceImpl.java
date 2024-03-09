package name.svetov.posttimeline.service;

import io.micronaut.serde.ObjectMapper;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import name.svetov.paging.model.Page;
import name.svetov.paging.model.Paging;
import name.svetov.post.model.Post;
import name.svetov.posttimeline.repository.PostTimelineRepository;
import name.svetov.userdetails.service.CurrentUserService;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

import static name.svetov.cache.CacheConstants.DELIMITER;
import static name.svetov.cache.CacheConstants.POST_PREFIX;

@Singleton
@RequiredArgsConstructor
public class PostTimelineServiceImpl implements PostTimelineService {
    private final CurrentUserService currentUserService;
    private final PostTimelineRepository postTimelineRepository;
    private final ObjectMapper objectMapper;

    @Override
    public Publisher<Page<String>> getTimeline(Paging paging) {
        return Mono.from(currentUserService.getCurrentUser())
            .flatMapMany(currentUser ->
                Flux.from(postTimelineRepository.findAllByKey(generateKey(currentUser.getId()), paging))
            );
    }

    @SneakyThrows
    @Override
    public Publisher<Boolean> addEntry(UUID userId, Post post) {
        return postTimelineRepository.addEntry(
            generateKey(userId),
            generateScore(post.getCreatedDate()),
            objectMapper.writeValueAsString(post)
        );
    }

    @SneakyThrows
    @Override
    public Publisher<Boolean> updateEntry(UUID userId, Post previousState, Post actualState) {
        var key = generateKey(userId);
        return Mono.from(postTimelineRepository.deleteEntry(key, objectMapper.writeValueAsString(previousState)))
            .then(
                Mono.from(
                    postTimelineRepository.addEntry(
                        key,
                        generateScore(actualState.getUpdatedDate()),
                        objectMapper.writeValueAsString(actualState)
                    )
                )
            );
    }

    @SneakyThrows
    @Override
    public Publisher<Boolean> deleteEntry(UUID userId, Post post) {
        return Mono.from(postTimelineRepository.deleteEntry(
                generateKey(userId),
                objectMapper.writeValueAsString(post)
            )
        );
    }

    private String generateKey(Object key) {
        return POST_PREFIX + DELIMITER + key.toString();
    }

    private double generateScore(OffsetDateTime timestamp) {
        return ZonedDateTime.of(timestamp.toLocalDateTime(), ZoneId.systemDefault()).toEpochSecond();
    }
}
