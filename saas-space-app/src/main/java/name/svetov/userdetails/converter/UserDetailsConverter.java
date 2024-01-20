package name.svetov.userdetails.converter;

import name.svetov.userdetails.dto.UserDetailsDto;
import name.svetov.userdetails.model.UserDetails;
import org.mapstruct.*;

import java.util.UUID;

@Mapper(
    componentModel = MappingConstants.ComponentModel.JAKARTA,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR,
    imports = UUID.class
)
public interface UserDetailsConverter {

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    UserDetails map(UserDetailsDto source);

    UserDetailsDto map(UserDetails source);

}
