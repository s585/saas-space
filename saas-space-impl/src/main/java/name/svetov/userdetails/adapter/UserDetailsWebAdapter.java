package name.svetov.userdetails.adapter;

import name.svetov.userdetails.dto.UserDetailsDto;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserDetailsWebAdapter {
    Mono<UserDetailsDto> getOneById(UUID userDetailsId);
}
