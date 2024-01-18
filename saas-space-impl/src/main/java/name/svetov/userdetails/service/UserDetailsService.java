package name.svetov.userdetails.service;

import name.svetov.userdetails.model.UserDetails;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserDetailsService {
    Mono<UserDetails> getOneById(UUID userDetailsId);

    Mono<UserDetails> getOneByUsername(String username);

    Mono<Boolean> existsByUsername(String username);

    Mono<UserDetails> create(UserDetails userDetails);
}
