package name.svetov.dialogue.model;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Dialogue {
    private UUID id;
    @Singular
    private Set<UUID> participants;
    private OffsetDateTime createdDate;
    private OffsetDateTime updatedDate;
}
