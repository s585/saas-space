package name.svetov.dialogue.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Serdeable.Deserializable
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class DialogueRq {
    private Set<UUID> participants;
}
