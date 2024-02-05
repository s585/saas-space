package name.svetov.userdetails.service;

import io.micronaut.context.annotation.Requires;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import name.svetov.userdetails.model.SearchUserCmd;
import name.svetov.userdetails.model.UserDetails;
import name.svetov.userdetails.repository.UserDetailsReactiveRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Singleton
@Requires(property = "micronaut.application.type", value = "reactive")
@RequiredArgsConstructor
public class UserDetailsReactiveServiceImpl implements UserDetailsReactiveService {

    private final UserDetailsReactiveRepository userDetailsReactiveRepository;

    @Override
    public Mono<UserDetails> getOneById(UUID userDetailsId) {
        return userDetailsReactiveRepository.getOneById(userDetailsId);
    }

    @Override
    public Mono<UserDetails> getOneByUsername(String username) {
        return userDetailsReactiveRepository.getOneByUsername(username);
    }

    @Override
    public Mono<Boolean> existsByUsername(String username) {
        return userDetailsReactiveRepository.existsByUsername(username);
    }

    @Override
    public Mono<UserDetails> create(UserDetails userDetails) {
        return userDetailsReactiveRepository.add(userDetails)
            .then(userDetailsReactiveRepository.getOneById(userDetails.getId()));
    }

    @Override
    public Flux<UserDetails> search(SearchUserCmd cmd) {
        return userDetailsReactiveRepository.search(cmd);
    }
}
