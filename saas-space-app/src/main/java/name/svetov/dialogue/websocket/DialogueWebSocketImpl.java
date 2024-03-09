package name.svetov.dialogue.websocket;

import io.micronaut.websocket.WebSocketBroadcaster;
import io.micronaut.websocket.WebSocketSession;
import io.micronaut.websocket.annotation.OnClose;
import io.micronaut.websocket.annotation.OnMessage;
import io.micronaut.websocket.annotation.OnOpen;
import io.micronaut.websocket.annotation.ServerWebSocket;
import lombok.RequiredArgsConstructor;
import name.svetov.dialogue.adapter.DialogueWebAdapter;
import name.svetov.dialogue.dto.DialogueMessageDto;
import name.svetov.userdetails.service.CurrentUserService;
import name.svetov.websocket.service.WebSocketSessionService;
import org.apache.commons.lang3.StringUtils;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static name.svetov.constants.EndpointConstants.DIALOGUE_WEB_SOCKET_ENDPOINT;
import static name.svetov.constants.TextConstants.COLON;
import static name.svetov.websocket.constant.WebSocketConstants.DIALOGUE_WEBSOCKET_CHANNEL_GROUP;

@ServerWebSocket(uri = DIALOGUE_WEB_SOCKET_ENDPOINT + "/{dialogueId}")
@RequiredArgsConstructor
public class DialogueWebSocketImpl implements DialogueWebSocket {
    private final CurrentUserService currentUserService;
    private final DialogueWebAdapter dialogueWebAdapter;
    private final WebSocketSessionService webSocketSessionService;
    private final WebSocketBroadcaster webSocketBroadcaster;

    @OnOpen
    public Publisher<Void> onOpen(UUID dialogueId, WebSocketSession session) {
        return Mono.from(currentUserService.getCurrentUser())
            .flatMap(user -> Mono.from(
                    webSocketSessionService.addSession(
                        generateDialogueWebSocketSessionGroupId(dialogueId),
                        user.getId(),
                        session
                    )
                )
            ).flatMapMany(persistedSession ->
                Flux.from(dialogueWebAdapter.findAllMessagesByDialogueId(persistedSession.getKey()))
                    .flatMap(message ->
                        webSocketBroadcaster.broadcast(
                            message,
                            openSession -> StringUtils.equals(openSession.getId(), persistedSession.getRight())
                        )
                    )
            ).then();
    }

    @OnMessage
    public Publisher<Void> onMessage(UUID dialogueId, DialogueMessageDto message) {
        return Mono.from(currentUserService.getCurrentUser())
            .flatMap(user -> Mono.from(
                    dialogueWebAdapter.createMessage(
                        message.toBuilder()
                            .dialogueId(dialogueId)
                            .authorId(user.getId())
                            .build()
                    )
                )
            ).flatMap(persistedMessage -> Mono.from(
                        webSocketSessionService.findSessionId(
                            generateDialogueWebSocketSessionGroupId(dialogueId),
                            persistedMessage.getRecipientId()
                        )
                    )
                    .flatMap(openSessionId ->
                        Mono.from(
                            webSocketBroadcaster.broadcast(persistedMessage, session -> StringUtils.equals(session.getId(), openSessionId))
                        )
                    )
            ).then();
    }

    @OnClose
    public Publisher<Void> onClose(UUID dialogueId) {
        return Mono.from(currentUserService.getCurrentUser())
            .flatMap(user -> Mono.from(
                    webSocketSessionService.deleteSession(
                        generateDialogueWebSocketSessionGroupId(dialogueId),
                        user.getId()
                    )
                )
            )
            .then();
    }

    private String generateDialogueWebSocketSessionGroupId(UUID dialogueId) {
        return DIALOGUE_WEBSOCKET_CHANNEL_GROUP + COLON + dialogueId;
    }
}
