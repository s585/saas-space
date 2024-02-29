package name.svetov.rabbitmq;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import io.micronaut.rabbitmq.connect.ChannelInitializer;
import jakarta.inject.Singleton;

import java.io.IOException;

import static name.svetov.rabbitmq.constants.ChannelConstants.*;

@Singleton
public class ChannelPoolListener extends ChannelInitializer {
    @Override
    public void initialize(Channel channel, String name) throws IOException {
        channel.exchangeDeclare(POST_EXCHANGE, BuiltinExchangeType.DIRECT, true);
        channel.queueDeclare(POST_CREATED_QUEUE, true, false, false, null);
        channel.queueBind(POST_CREATED_QUEUE, POST_EXCHANGE, POST_CREATED_QUEUE_ROUTING_KEY);
        channel.queueDeclare(POST_UPDATED_QUEUE, true, false, false, null);
        channel.queueBind(POST_UPDATED_QUEUE, POST_EXCHANGE, POST_UPDATED_QUEUE_ROUTING_KEY);
        channel.queueDeclare(POST_DELETED_QUEUE, true, false, false, null);
        channel.queueBind(POST_DELETED_QUEUE, POST_EXCHANGE, POST_DELETED_QUEUE_ROUTING_KEY);
    }
}
