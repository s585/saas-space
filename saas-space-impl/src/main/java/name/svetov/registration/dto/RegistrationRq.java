package name.svetov.registration.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

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
    private LocalDate birthDate;
    private String sex;
    private String country;
    private String city;
    private Set<String> hobbies;
}
