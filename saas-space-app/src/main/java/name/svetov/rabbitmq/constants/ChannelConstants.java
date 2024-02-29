package name.svetov.rabbitmq.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ChannelConstants {
    public static final String POST_EXCHANGE = "post.exchange";
    public static final String POST_CREATED_QUEUE = "post.created.queue";
    public static final String POST_UPDATED_QUEUE = "post.updated.queue";
    public static final String POST_DELETED_QUEUE = "post.deleted.queue";
    public static final String POST_CREATED_QUEUE_ROUTING_KEY = "post.created";
    public static final String POST_UPDATED_QUEUE_ROUTING_KEY = "post.updated";
    public static final String POST_DELETED_QUEUE_ROUTING_KEY = "post.deleted";
}
