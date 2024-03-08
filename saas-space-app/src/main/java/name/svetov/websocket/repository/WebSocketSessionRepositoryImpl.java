package name.svetov.websocket.repository;

import io.lettuce.core.api.StatefulRedisConnection;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;

@Singleton
@RequiredArgsConstructor
public class WebSocketSessionRepositoryImpl implements WebSocketSessionRepository {
    private final StatefulRedisConnection<String, String> connection;

    @Override
    public Publisher<String> findSessionId(String key, String field) {
        return connection.reactive().hget(key, field);
    }

    @Override
    public Publisher<Boolean> addSession(String key, String field, String value) {
        return connection.reactive().hset(key, field, value);
    }

    @Override
    public Publisher<Boolean> deleteSession(String key, String field) {
        return connection.reactive().hdel(key, field)
            .map(response -> response == 1);
    }
}
