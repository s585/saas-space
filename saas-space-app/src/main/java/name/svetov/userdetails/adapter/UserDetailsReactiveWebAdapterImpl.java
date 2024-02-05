package name.svetov.userdetails.adapter;

import io.micronaut.context.annotation.Requires;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import name.svetov.userdetails.converter.UserDetailsConverter;
import name.svetov.userdetails.dto.SearchUserRq;
import name.svetov.userdetails.dto.UserDetailsDto;
import name.svetov.userdetails.service.UserDetailsReactiveService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Singleton
@Requires(property = "micronaut.application.type", value = "reactive")
@RequiredArgsConstructor
public class UserDetailsReactiveWebAdapterImpl implements UserDetailsReactiveWebAdapter {
    private final UserDetailsReactiveService userDetailsReactiveService;
    private final UserDetailsConverter converter;

    @Override
    public Mono<UserDetailsDto> getOneById(UUID userDetailsId) {
        return userDetailsReactiveService.getOneById(userDetailsId)
            .map(converter::map);
    }

    @Override
    public Flux<UserDetailsDto> search(SearchUserRq rq) {
        return userDetailsReactiveService.search(converter.map(rq))
            .map(converter::map);
    }
}
