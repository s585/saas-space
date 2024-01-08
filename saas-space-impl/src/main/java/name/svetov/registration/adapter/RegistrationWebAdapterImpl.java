package name.svetov.registration.adapter;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import name.svetov.registration.converter.RegistrationRqConverter;
import name.svetov.registration.dto.RegistrationRq;
import name.svetov.registration.service.RegistrationService;
import name.svetov.userdetails.converter.UserDetailsConverter;
import name.svetov.userdetails.dto.UserDetailsDto;
import reactor.core.publisher.Mono;

@Singleton
@RequiredArgsConstructor
public class RegistrationWebAdapterImpl implements RegistrationWebAdapter {
    private final RegistrationService registrationService;
    private final RegistrationRqConverter signupConverter;
    private final UserDetailsConverter userDetailsConverter;

    @Override
    public Mono<UserDetailsDto> signup(RegistrationRq rq) {
        return registrationService.signup(
            signupConverter.convert(rq)
        ).map(userDetailsConverter::map);
    }
}
