package name.svetov.registration.controller;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.annotation.Controller;
import lombok.RequiredArgsConstructor;
import name.svetov.registration.adapter.RegistrationWebAdapter;
import name.svetov.registration.dto.RegistrationRq;
import name.svetov.userdetails.dto.UserDetailsDto;

@Controller
@Requires(property = "micronaut.application.type", value = "blocking")
@RequiredArgsConstructor
public class RegistrationControllerImpl implements RegistrationController {
    private final RegistrationWebAdapter registrationWebReactiveAdapter;

    @Override
    public UserDetailsDto register(RegistrationRq rq) {
        return registrationWebReactiveAdapter.register(rq);
    }
}
