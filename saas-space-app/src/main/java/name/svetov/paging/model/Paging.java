package name.svetov.paging.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Paging {
    private Long currentPage;
    private Long totalPageAmount;
    private Long recordsOnPage;
    private Long totalRecordAmount;
}
