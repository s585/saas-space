package name.svetov.posttimeline.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import name.svetov.paging.dto.PagingRs;

import java.util.Collections;
import java.util.List;

@Serdeable.Serializable
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class PostTimelineRs {
    @Builder.Default
    private List<String> data = Collections.emptyList();
    @Builder.Default
    private PagingRs paging = PagingRs.EMPTY;
}
