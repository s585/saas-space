package name.svetov.registration.converter;

import name.svetov.config.SaasMapperDefaultConfig;
import name.svetov.registration.dto.RegistrationRq;
import name.svetov.registration.model.RegistrationCmd;
import name.svetov.userdetails.model.UserDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = SaasMapperDefaultConfig.class)
public interface RegistrationConverter {
    RegistrationCmd convert(RegistrationRq rq);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "celebrity", ignore = true)
    UserDetails convert(RegistrationCmd cmd);
}
