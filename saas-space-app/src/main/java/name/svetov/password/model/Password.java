package name.svetov.password.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Password {
    private UUID id;
    private UUID userDetailsId;
    private String secret;
    private OffsetDateTime createdDate;
    private OffsetDateTime updatedDate;
}
