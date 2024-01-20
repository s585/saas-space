package name.svetov.password.service;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import name.svetov.password.model.Password;
import name.svetov.password.repository.PasswordRepository;
import reactor.core.publisher.Mono;

@Singleton
@RequiredArgsConstructor
public class PasswordServiceImpl implements PasswordService {
    private final PasswordRepository passwordRepository;

    @Override
    public Mono<Boolean> create(Password password) {
        return passwordRepository.add(password);
    }
}
