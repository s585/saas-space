package name.svetov.post.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Serdeable
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private UUID id;
    private UUID authorId;
    private String body;
}
