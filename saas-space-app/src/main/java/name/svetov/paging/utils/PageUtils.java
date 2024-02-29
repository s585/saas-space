package name.svetov.paging.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import name.svetov.paging.model.Page;
import name.svetov.paging.model.Paging;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PageUtils {
    public static long calcTotalPages(long totalRows, long pageSize) {
        return totalRows > 0
            ? Math.round(Math.ceil((double) totalRows / pageSize))
            : 0L;
    }

    public static long calcCurrentPage(long offset, long pageSize) {
        return pageSize > 0
            ? offset / pageSize
            : 0L;
    }

    public static <T> Page<T> buildPage(List<T> data, long totalRecordAmount, long offset, long limit) {
        return Page.<T>builder()
            .data(data)
            .paging(
                Paging.builder()
                    .currentPage(PageUtils.calcCurrentPage(offset, limit))
                    .totalPageAmount(PageUtils.calcTotalPages(totalRecordAmount, limit))
                    .recordsOnPage((long) data.size())
                    .totalRecordAmount(totalRecordAmount)
                    .build()
            )
            .build();
    }
}
