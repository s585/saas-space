package name.svetov.userdetails.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Serdeable.Serializable
@Serdeable.Deserializable
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDto {
    private UUID id;
    private String username;
    private String firstName;
    private String lastName;
    private byte age;
    private String sex;
    private String country;
    private String city;
    private Set<String> hobbies;
}
