package name.svetov.post.rabbitmq.event;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import name.svetov.post.model.Post;

@Serdeable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PostDeletedEvent {
    private Post post;
}
