package name.svetov.userdetails.service;

import io.micronaut.security.utils.SecurityService;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import name.svetov.userdetails.model.UserDetails;
import name.svetov.userdetails.repository.UserDetailsRepository;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Singleton
@RequiredArgsConstructor
public class CurrentUserServiceImpl implements CurrentUserService {
    private final UserDetailsRepository userDetailsRepository;
    private final SecurityService securityService;

    @Override
    public Publisher<UUID> getCurrentUserId() {
        return Mono.just(securityService.username())
            .flatMap(optional -> optional.map(Mono::just).orElseGet(Mono::empty))
            .flatMap(username -> Mono.from(userDetailsRepository.getOneByUsername(username)))
            .map(UserDetails::getId);
    }
}
