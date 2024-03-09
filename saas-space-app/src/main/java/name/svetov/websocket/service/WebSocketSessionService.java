package name.svetov.websocket.service;

import io.micronaut.websocket.WebSocketSession;
import org.apache.commons.lang3.tuple.Pair;
import org.reactivestreams.Publisher;

import java.util.UUID;

public interface WebSocketSessionService {
    Publisher<String> findSessionId(String channelGroup, UUID userId);

    Publisher<Pair<UUID, String>> addSession(String channelGroup, UUID userId, WebSocketSession session);

    Publisher<Boolean> deleteSession(String channelGroup, UUID userId);
}
