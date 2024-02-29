package name.svetov.posttimeline.repository;

import io.lettuce.core.ScoredValue;
import io.lettuce.core.api.StatefulRedisConnection;
import io.micronaut.context.annotation.Property;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import name.svetov.paging.model.Page;
import name.svetov.paging.model.Paging;
import name.svetov.paging.utils.PageUtils;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Optional;

@Singleton
@RequiredArgsConstructor
public class PostTimelineRepositoryImpl implements PostTimelineRepository {
    private final StatefulRedisConnection<String, String> connection;

    @Property(name = "space-app.post.timeline.limit")
    private long timelineSizeLimit;

    @Override
    public Publisher<Page<String>> findAllByKey(String key, Paging paging) {
        long offset = paging.getRecordsOnPage() * paging.getCurrentPage();
        long limit = paging.getRecordsOnPage();
        return connection.reactive().zrevrange(key, offset, offset + limit)
            .collectList()
            .flatMap(
                data -> connection.reactive().zcard(key)
                    .flatMap(totalRecordAmount -> Mono.just(PageUtils.buildPage(data, totalRecordAmount, offset, limit)))
            );
    }

    @Override
    public Publisher<Boolean> addEntry(String key, double score, String value) {
        return connection.reactive().zcard(key)
            .flatMap(totalRecordsAmount ->
                connection.reactive().zadd(key, ScoredValue.from(score, Optional.of(value)))
                    .publishOn(Schedulers.boundedElastic())
                    .doFirst(() -> {
                            if (totalRecordsAmount >= timelineSizeLimit) {
                                connection.reactive().zpopmin(key)
                                    .subscribe();
                            }
                        }
                    )
            )
            .map(response -> response != 0);
    }

    @Override
    public Publisher<Boolean> deleteEntry(String key, String value) {
        return connection.reactive().zrem(key, value)
            .map(response -> response == 1);
    }
}
