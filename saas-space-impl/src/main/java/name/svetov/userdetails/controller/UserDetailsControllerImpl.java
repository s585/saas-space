package name.svetov.userdetails.controller;

import io.micronaut.http.annotation.Controller;
import lombok.RequiredArgsConstructor;
import name.svetov.userdetails.adapter.UserDetailsWebAdapter;
import name.svetov.userdetails.dto.UserDetailsDto;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class UserDetailsControllerImpl implements UserDetailsController {
    private final UserDetailsWebAdapter webAdapter;

    @Override
    public Mono<UserDetailsDto> getOneById(UUID userDetailsId) {
        return webAdapter.getOneById(userDetailsId);
    }
}
