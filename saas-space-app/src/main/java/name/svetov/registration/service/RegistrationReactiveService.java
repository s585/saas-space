package name.svetov.registration.service;

import name.svetov.registration.model.RegistrationCmd;
import name.svetov.userdetails.model.UserDetails;
import reactor.core.publisher.Mono;

public interface RegistrationReactiveService {
    Mono<UserDetails> register(RegistrationCmd cmd);
}
