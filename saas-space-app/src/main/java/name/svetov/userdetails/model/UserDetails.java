package name.svetov.userdetails.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import name.svetov.password.model.Password;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {
    private UUID id;
    private String username;
    private Password password;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String sex;
    private String country;
    private String city;
    private Set<String> hobbies;
    private boolean celebrity;
    private OffsetDateTime createdDate;
    private OffsetDateTime updatedDate;
}
