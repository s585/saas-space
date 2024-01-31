package name.svetov.userdetails.model;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Serdeable.Serializable
@Serdeable.Deserializable
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class SearchUserCmd {
    private String firstName;
    private String lastName;
}
