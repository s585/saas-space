package name.svetov.password.service;

import io.micronaut.context.annotation.Requires;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import name.svetov.password.model.Password;
import name.svetov.password.repository.PasswordReactiveRepository;
import reactor.core.publisher.Mono;

@Singleton
@Requires(property = "micronaut.application.type", value = "reactive")
@RequiredArgsConstructor
public class PasswordReactiveServiceImpl implements PasswordReactiveService {
    private final PasswordReactiveRepository passwordReactiveRepository;

    @Override
    public Mono<Boolean> create(Password password) {
        return passwordReactiveRepository.add(password);
    }
}
