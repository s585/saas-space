package name.svetov.userdetails.converter;

import name.svetov.config.SaasMapperDefaultConfig;
import name.svetov.userdetails.dto.SearchUserRq;
import name.svetov.userdetails.dto.UserDetailsDto;
import name.svetov.userdetails.model.SearchUserCmd;
import name.svetov.userdetails.model.UserDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = SaasMapperDefaultConfig.class)
public interface UserDetailsConverter {

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "celebrity", ignore = true)
    UserDetails map(UserDetailsDto source);

    UserDetailsDto map(UserDetails source);

    List<UserDetailsDto> map(List<UserDetails> source);

    SearchUserCmd map(SearchUserRq source);
}
