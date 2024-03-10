package name.svetov.posttimeline.websocket;

import io.micronaut.websocket.WebSocketSession;
import io.micronaut.websocket.annotation.OnClose;
import io.micronaut.websocket.annotation.OnMessage;
import io.micronaut.websocket.annotation.OnOpen;
import io.micronaut.websocket.annotation.ServerWebSocket;
import lombok.RequiredArgsConstructor;
import name.svetov.userdetails.service.CurrentUserService;
import name.svetov.websocket.service.WebSocketSessionService;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import static name.svetov.constants.EndpointConstants.POST_TIMELINE_WEB_SOCKET_ENDPOINT;
import static name.svetov.websocket.constant.WebSocketConstants.POST_TIMELINE_WEBSOCKET_CHANNEL_GROUP;

@ServerWebSocket(uri = POST_TIMELINE_WEB_SOCKET_ENDPOINT)
@RequiredArgsConstructor
public class PostTimelineWebSocketImpl implements PostTimelineWebSocket {
    private final WebSocketSessionService webSocketSessionService;
    private final CurrentUserService currentUserService;

    @OnOpen
    public Publisher<Void> onOpen(WebSocketSession session) {
        return Mono.from(currentUserService.getCurrentUser())
                    .flatMap(user -> Mono.from(
                            webSocketSessionService.addSession(
                                POST_TIMELINE_WEBSOCKET_CHANNEL_GROUP,
                                user.getId(),
                                session
                            )
                        ).then()
            );
    }

    @OnMessage
    public Publisher<Void> onMessage(String message) {
        return Mono.empty();
    }

    @OnClose
    public Publisher<Void> onClose() {
        return Mono.from(currentUserService.getCurrentUser())
            .flatMap(user -> Mono.from(webSocketSessionService.deleteSession(POST_TIMELINE_WEBSOCKET_CHANNEL_GROUP, user.getId())))
            .then();
    }
}
