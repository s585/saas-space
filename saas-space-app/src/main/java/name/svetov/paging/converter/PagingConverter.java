package name.svetov.paging.converter;


import name.svetov.config.SaasMapperDefaultConfig;
import name.svetov.paging.dto.PagingRq;
import name.svetov.paging.dto.PagingRs;
import name.svetov.paging.model.Paging;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = SaasMapperDefaultConfig.class)
public interface PagingConverter {
    PagingRs convert(Paging source);

    @Mapping(target = "totalRecordAmount", ignore = true)
    @Mapping(target = "totalPageAmount", ignore = true)
    @Mapping(target = "recordsOnPage", source = "itemsPerPage")
    @Mapping(target = "currentPage", source = "pageNum")
    Paging convert(PagingRq source);
}
