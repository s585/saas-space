package name.svetov.config;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.MapperConfig;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

@MapperConfig(
    componentModel = MappingConstants.ComponentModel.JAKARTA,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR,
    imports = UUID.class
)
public interface SaasMapperDefaultConfig {
}
