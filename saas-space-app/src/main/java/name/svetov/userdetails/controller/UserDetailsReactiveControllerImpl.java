package name.svetov.userdetails.controller;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.annotation.Controller;
import lombok.RequiredArgsConstructor;
import name.svetov.userdetails.adapter.UserDetailsReactiveWebAdapter;
import name.svetov.userdetails.dto.SearchUserRq;
import name.svetov.userdetails.dto.UserDetailsDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Controller
@Requires(property = "micronaut.application.type", value = "reactive")
@RequiredArgsConstructor
public class UserDetailsReactiveControllerImpl implements UserDetailsReactiveController {
    private final UserDetailsReactiveWebAdapter webAdapter;

    @Override
    public Mono<UserDetailsDto> getOneById(UUID userDetailsId) {
        return webAdapter.getOneById(userDetailsId);
    }

    @Override
    public Flux<UserDetailsDto> search(SearchUserRq rq) {
        return webAdapter.search(rq);
    }
}
