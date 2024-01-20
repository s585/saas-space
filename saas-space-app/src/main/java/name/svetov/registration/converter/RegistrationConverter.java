package name.svetov.registration.converter;

import name.svetov.registration.dto.RegistrationRq;
import name.svetov.registration.model.RegistrationCmd;
import name.svetov.userdetails.model.UserDetails;
import org.mapstruct.*;

import java.util.UUID;

@Mapper(
    componentModel = MappingConstants.ComponentModel.JAKARTA,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR,
    imports = UUID.class
)
public interface RegistrationConverter {
    RegistrationCmd convert(RegistrationRq rq);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    UserDetails convert(RegistrationCmd cmd);
}
