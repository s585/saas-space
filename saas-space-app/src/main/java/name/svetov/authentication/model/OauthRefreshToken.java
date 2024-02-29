package name.svetov.authentication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OauthRefreshToken {
    private UUID id;
    private String username;
    private String refreshToken;
    private boolean revoked;
    private OffsetDateTime createdDate;
    private OffsetDateTime updatedDate;
}
