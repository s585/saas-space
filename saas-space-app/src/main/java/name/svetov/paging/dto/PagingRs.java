package name.svetov.paging.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.apache.commons.lang3.math.NumberUtils;

@Serdeable.Serializable
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
    {
        PagingRs.Fields.currentPage,
        PagingRs.Fields.totalPageAmount,
        PagingRs.Fields.recordsOnPage,
        PagingRs.Fields.totalRecordAmount,
    }
)
@FieldNameConstants
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class PagingRs {
    public static final PagingRs EMPTY = PagingRs.builder()
        .currentPage(NumberUtils.LONG_ZERO)
        .totalPageAmount(NumberUtils.LONG_ZERO)
        .recordsOnPage(NumberUtils.LONG_ZERO)
        .totalRecordAmount(NumberUtils.LONG_ZERO)
        .build();

    private Long currentPage;
    private Long totalPageAmount;
    private Long recordsOnPage;
    private Long totalRecordAmount;
}
