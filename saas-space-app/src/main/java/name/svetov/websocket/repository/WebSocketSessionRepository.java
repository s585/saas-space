package name.svetov.websocket.repository;

import org.reactivestreams.Publisher;

public interface WebSocketSessionRepository {
    Publisher<String> findSessionId(String key, String field);

    Publisher<Boolean> addSession(String key, String field, String value);

    Publisher<Boolean> deleteSession(String key, String field);
}
