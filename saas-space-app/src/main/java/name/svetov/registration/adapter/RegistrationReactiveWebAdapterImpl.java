package name.svetov.registration.adapter;

import io.micronaut.context.annotation.Requires;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import name.svetov.registration.converter.RegistrationConverter;
import name.svetov.registration.dto.RegistrationRq;
import name.svetov.registration.service.RegistrationReactiveService;
import name.svetov.userdetails.converter.UserDetailsConverter;
import name.svetov.userdetails.dto.UserDetailsDto;
import reactor.core.publisher.Mono;

@Singleton
@Requires(property = "micronaut.application.type", value = "reactive")
@RequiredArgsConstructor
public class RegistrationReactiveWebAdapterImpl implements RegistrationReactiveWebAdapter {
    private final RegistrationReactiveService registrationReactiveService;
    private final RegistrationConverter signupConverter;
    private final UserDetailsConverter userDetailsConverter;

    @Override
    public Mono<UserDetailsDto> register(RegistrationRq rq) {
        return registrationReactiveService.register(
            signupConverter.convert(rq)
        ).map(userDetailsConverter::map);
    }
}
