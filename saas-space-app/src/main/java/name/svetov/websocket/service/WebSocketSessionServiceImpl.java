package name.svetov.websocket.service;

import io.micronaut.websocket.WebSocketSession;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import name.svetov.websocket.repository.WebSocketSessionRepository;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Singleton
@RequiredArgsConstructor
public class WebSocketSessionServiceImpl implements WebSocketSessionService {
    private final WebSocketSessionRepository repository;

    @Override
    public Publisher<String> findSessionId(String channelGroup, UUID userId) {
        return Mono.from(repository.findSessionId(channelGroup, String.valueOf(userId)));
    }

    @SneakyThrows
    @Override
    public Publisher<Boolean> addSession(String channelGroup, UUID userId, WebSocketSession session) {
        return repository.addSession(
            channelGroup,
            String.valueOf(userId),
            session.getId()
        );
    }

    @Override
    public Publisher<Boolean> deleteSession(String channelGroup, UUID userId) {
        return repository.deleteSession(channelGroup, String.valueOf(userId));
    }
}
