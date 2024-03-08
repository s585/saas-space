package name.svetov.posttimeline.rabbitmq.listener;

import io.micronaut.rabbitmq.annotation.Queue;
import io.micronaut.rabbitmq.annotation.RabbitListener;
import io.micronaut.websocket.WebSocketBroadcaster;
import io.micronaut.websocket.WebSocketSession;
import lombok.RequiredArgsConstructor;
import name.svetov.post.rabbitmq.event.PostCreatedEvent;
import name.svetov.post.rabbitmq.event.PostDeletedEvent;
import name.svetov.post.rabbitmq.event.PostUpdatedEvent;
import name.svetov.posttimeline.service.PostTimelineService;
import name.svetov.userrelationships.service.UserRelationshipsService;
import name.svetov.websocket.service.WebSocketSessionService;
import org.apache.commons.lang3.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static name.svetov.rabbitmq.constants.ChannelConstants.*;
import static name.svetov.websocket.constant.WebSocketConstants.POST_TIMELINE_WEBSOCKET_CHANNEL_GROUP;

@RabbitListener
@RequiredArgsConstructor
public class PostEventListenerImpl implements PostEventListener {
    private final UserRelationshipsService userRelationshipsService;
    private final PostTimelineService postTimelineService;
    private final WebSocketSessionService webSocketSessionService;
    private final WebSocketBroadcaster webSocketBroadcaster;

    @Override
    @Queue(POST_CREATED_QUEUE)
    public void process(PostCreatedEvent event) {
        var post = event.getPost();
        Flux.from(userRelationshipsService.findAllFriendsByUserId(post.getAuthorId())).parallel()
            .flatMap(recipient ->
                Mono.from(postTimelineService.addEntry(recipient, post))
                    .flatMap(result -> Mono.from(webSocketSessionService.findSessionId(POST_TIMELINE_WEBSOCKET_CHANNEL_GROUP, recipient)))
                    .flatMap(sessionId ->
                        Mono.from(webSocketBroadcaster.broadcast(
                                post,
                                (WebSocketSession session) -> StringUtils.equals(session.getId(), sessionId)
                            )
                        )
                    )
            ).subscribe();
    }

    @Override
    @Queue(POST_UPDATED_QUEUE)
    public void process(PostUpdatedEvent event) {
        Flux.from(userRelationshipsService.findAllFriendsByUserId(event.getActualState().getAuthorId())).parallel()
            .flatMap(recipient -> postTimelineService.updateEntry(recipient, event.getPreviousState(), event.getActualState()))
            .subscribe();
    }

    @Override
    @Queue(POST_DELETED_QUEUE)
    public void process(PostDeletedEvent event) {
        Flux.from(userRelationshipsService.findAllFriendsByUserId(event.getPost().getAuthorId())).parallel()
            .flatMap(recipient -> postTimelineService.deleteEntry(recipient, event.getPost()))
            .subscribe();
    }
}
