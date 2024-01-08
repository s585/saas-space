package name.svetov.registration.service;

import name.svetov.registration.model.RegistrationCmd;
import name.svetov.userdetails.model.UserDetails;
import reactor.core.publisher.Mono;

public interface RegistrationService {
    Mono<UserDetails> signup(RegistrationCmd cmd);
}
