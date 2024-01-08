package name.svetov.password.service;

import name.svetov.password.model.Password;
import reactor.core.publisher.Mono;

public interface PasswordService {
    Mono<Boolean> create(Password password);
}
