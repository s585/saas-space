package name.svetov.registration.adapter;

import io.micronaut.context.annotation.Requires;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import name.svetov.registration.converter.RegistrationConverter;
import name.svetov.registration.dto.RegistrationRq;
import name.svetov.registration.service.RegistrationService;
import name.svetov.userdetails.converter.UserDetailsConverter;
import name.svetov.userdetails.dto.UserDetailsDto;

@Singleton
@Requires(property = "micronaut.application.type", value = "blocking")
@RequiredArgsConstructor
public class RegistrationWebAdapterImpl implements RegistrationWebAdapter {
    private final RegistrationService registrationService;
    private final RegistrationConverter signupConverter;
    private final UserDetailsConverter userDetailsConverter;

    @Override
    public UserDetailsDto register(RegistrationRq rq) {
        return userDetailsConverter.map(
            registrationService.register(signupConverter.convert(rq))
        );
    }
}
