package name.svetov.registration.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Serdeable.Deserializable
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRq {
    private String username;
    private String firstName;
    private String lastName;
    private String rawPassword;
}
