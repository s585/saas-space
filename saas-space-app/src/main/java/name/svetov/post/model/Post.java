package name.svetov.post.model;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Serdeable
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private UUID id;
    private UUID authorId;
    private String body;
    private OffsetDateTime createdDate;
    private OffsetDateTime updatedDate;
}
