package name.svetov.userdetails.repository;

import name.svetov.userdetails.model.SearchUserCmd;
import name.svetov.userdetails.model.UserDetails;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserDetailsReactiveRepository {
    Mono<UserDetails> getOneById(UUID userDetailsId);

    Mono<UserDetails> getOneByUsername(String username);

    Mono<Boolean> existsByUsername(String username);

    Mono<Boolean> add(UserDetails userDetails);

    Flux<UserDetails> search(SearchUserCmd cmd);
}