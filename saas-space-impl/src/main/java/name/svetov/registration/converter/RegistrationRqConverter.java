package name.svetov.registration.converter;

import name.svetov.registration.dto.RegistrationRq;
import name.svetov.registration.model.RegistrationCmd;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

@Mapper(
    componentModel = MappingConstants.ComponentModel.JAKARTA,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR,
    imports = UUID.class
)
public interface RegistrationRqConverter {
    RegistrationCmd convert(RegistrationRq rq);
}
