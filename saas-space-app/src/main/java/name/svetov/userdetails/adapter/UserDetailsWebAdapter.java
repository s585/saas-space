package name.svetov.userdetails.adapter;

import name.svetov.userdetails.dto.SearchUserRq;
import name.svetov.userdetails.dto.UserDetailsDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserDetailsWebAdapter {
    Mono<UserDetailsDto> getOneById(UUID userDetailsId);

    Flux<UserDetailsDto> search(SearchUserRq rq);
}
