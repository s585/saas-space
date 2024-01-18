package name.svetov.registration.adapter;

import name.svetov.registration.dto.RegistrationRq;
import name.svetov.userdetails.dto.UserDetailsDto;
import reactor.core.publisher.Mono;

public interface RegistrationWebAdapter {
    Mono<UserDetailsDto> register(RegistrationRq rq);
}
