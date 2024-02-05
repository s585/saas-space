package name.svetov.password.repository;

import name.svetov.password.model.Password;
import reactor.core.publisher.Mono;

public interface PasswordReactiveRepository {
    Mono<Boolean> add(Password password);
}
