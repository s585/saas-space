package name.svetov.posttimeline.rabbitmq.listener;

import name.svetov.post.rabbitmq.event.PostCreatedEvent;
import name.svetov.post.rabbitmq.event.PostDeletedEvent;
import name.svetov.post.rabbitmq.event.PostUpdatedEvent;

public interface PostEventListener {
    void process(PostCreatedEvent event);

    void process(PostUpdatedEvent event);

    void process(PostDeletedEvent event);
}
