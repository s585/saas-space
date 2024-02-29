package name.svetov.posttimeline.rabbitmq.listener;

import io.micronaut.rabbitmq.annotation.Queue;
import io.micronaut.rabbitmq.annotation.RabbitListener;
import lombok.RequiredArgsConstructor;
import name.svetov.post.rabbitmq.event.PostDeletedEvent;
import name.svetov.post.rabbitmq.event.PostUpdatedEvent;
import name.svetov.posttimeline.service.PostTimelineService;
import name.svetov.post.rabbitmq.event.PostCreatedEvent;
import name.svetov.userrelationships.service.UserRelationshipsService;
import reactor.core.publisher.Flux;

import static name.svetov.rabbitmq.constants.ChannelConstants.*;

@RabbitListener
@RequiredArgsConstructor
public class PostEventListenerImpl implements PostEventListener {
    private final UserRelationshipsService userRelationshipsService;
    private final PostTimelineService postTimelineService;

    @Override
    @Queue(POST_CREATED_QUEUE)
    public void process(PostCreatedEvent event) {
        Flux.from(userRelationshipsService.findAllFriendsByUserId(event.getPost().getAuthorId())).parallel()
            .flatMap(recipient -> postTimelineService.addEntry(recipient, event.getPost()))
            .subscribe();
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
