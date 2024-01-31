package name.svetov.userdetails.service;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import name.svetov.userdetails.model.SearchUserCmd;
import name.svetov.userdetails.model.UserDetails;
import name.svetov.userdetails.repository.UserDetailsRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Singleton
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;

    @Override
    public Mono<UserDetails> getOneById(UUID userDetailsId) {
        return userDetailsRepository.getOneById(userDetailsId);
    }

    @Override
    public Mono<UserDetails> getOneByUsername(String username) {
        return userDetailsRepository.getOneByUsername(username);
    }

    @Override
    public Mono<Boolean> existsByUsername(String username) {
        return userDetailsRepository.existsByUsername(username);
    }

    @Override
    public Mono<UserDetails> create(UserDetails userDetails) {
        return userDetailsRepository.add(userDetails)
            .then(userDetailsRepository.getOneById(userDetails.getId()));
    }

    @Override
    public Flux<UserDetails> search(SearchUserCmd cmd) {
        return userDetailsRepository.search(cmd);
    }
}
