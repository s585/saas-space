package name.svetov.registration.adapter;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import name.svetov.registration.converter.RegistrationConverter;
import name.svetov.registration.dto.RegistrationRq;
import name.svetov.registration.service.RegistrationService;
import name.svetov.userdetails.converter.UserDetailsConverter;
import name.svetov.userdetails.dto.UserDetailsDto;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

@Singleton
@RequiredArgsConstructor
public class RegistrationWebAdapterImpl implements RegistrationWebAdapter {
    private final RegistrationService registrationService;
    private final RegistrationConverter signupConverter;
    private final UserDetailsConverter userDetailsConverter;

    @Override
    public Publisher<UserDetailsDto> register(RegistrationRq rq) {
        return Mono.from(registrationService.register(signupConverter.convert(rq)))
            .map(userDetailsConverter::map);
    }
}
