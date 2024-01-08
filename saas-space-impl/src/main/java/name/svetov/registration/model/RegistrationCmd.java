package name.svetov.registration.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationCmd {
    private String username;
    private String firstName;
    private String lastName;
    private String rawPassword;
}
