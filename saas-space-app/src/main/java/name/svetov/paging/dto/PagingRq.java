package name.svetov.paging.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Serdeable.Deserializable
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class PagingRq {
    public static final PagingRq DEFAULT = new PagingRq();

    @NotNull
    @Positive
    @Builder.Default
    private Long pageNum = 0L;

    @NotNull
    @Min(1)
    @Max(100)
    @Builder.Default
    private Long itemsPerPage = 0L;
}
