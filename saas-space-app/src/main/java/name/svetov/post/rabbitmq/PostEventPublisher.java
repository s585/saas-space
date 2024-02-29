package name.svetov.post.rabbitmq;

import io.micronaut.rabbitmq.annotation.Binding;
import io.micronaut.rabbitmq.annotation.Mandatory;
import io.micronaut.rabbitmq.annotation.RabbitClient;
import name.svetov.post.rabbitmq.event.PostCreatedEvent;
import name.svetov.post.rabbitmq.event.PostDeletedEvent;
import name.svetov.post.rabbitmq.event.PostUpdatedEvent;
import org.reactivestreams.Publisher;

import static name.svetov.rabbitmq.constants.ChannelConstants.*;

@RabbitClient(POST_EXCHANGE)
public interface PostEventPublisher {
    @Binding(POST_CREATED_QUEUE_ROUTING_KEY)
    @Mandatory
    Publisher<Void> publish(PostCreatedEvent event);

    @Binding(POST_UPDATED_QUEUE_ROUTING_KEY)
    @Mandatory
    Publisher<Void> publish(PostUpdatedEvent event);

    @Binding(POST_DELETED_QUEUE_ROUTING_KEY)
    @Mandatory
    Publisher<Void> publish(PostDeletedEvent event);
}
