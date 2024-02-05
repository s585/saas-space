package name.svetov.registration.controller;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.annotation.Controller;
import lombok.RequiredArgsConstructor;
import name.svetov.registration.adapter.RegistrationReactiveWebAdapter;
import name.svetov.registration.dto.RegistrationRq;
import name.svetov.userdetails.dto.UserDetailsDto;
import reactor.core.publisher.Mono;

@Controller
@Requires(property = "micronaut.application.type", value = "reactive")
@RequiredArgsConstructor
public class RegistrationReactiveControllerImpl implements RegistrationReactiveController {
    private final RegistrationReactiveWebAdapter registrationReactiveWebAdapter;

    @Override
    public Mono<UserDetailsDto> register(RegistrationRq rq) {
        return registrationReactiveWebAdapter.register(rq);
    }
}
