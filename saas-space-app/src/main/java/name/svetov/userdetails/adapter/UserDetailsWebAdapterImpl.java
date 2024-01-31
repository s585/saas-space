package name.svetov.userdetails.adapter;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import name.svetov.userdetails.converter.UserDetailsConverter;
import name.svetov.userdetails.dto.SearchUserRq;
import name.svetov.userdetails.dto.UserDetailsDto;
import name.svetov.userdetails.service.UserDetailsService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Singleton
@RequiredArgsConstructor
public class UserDetailsWebAdapterImpl implements UserDetailsWebAdapter {
    private final UserDetailsService userDetailsService;
    private final UserDetailsConverter converter;

    @Override
    public Mono<UserDetailsDto> getOneById(UUID userDetailsId) {
        return userDetailsService.getOneById(userDetailsId)
            .map(converter::map);
    }

    @Override
    public Flux<UserDetailsDto> search(SearchUserRq rq) {
        return userDetailsService.search(converter.map(rq))
            .map(converter::map);
    }
}
