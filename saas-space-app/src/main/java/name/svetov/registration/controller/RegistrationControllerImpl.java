package name.svetov.registration.controller;

import io.micronaut.http.annotation.Controller;
import lombok.RequiredArgsConstructor;
import name.svetov.registration.adapter.RegistrationWebAdapter;
import name.svetov.registration.dto.RegistrationRq;
import name.svetov.userdetails.dto.UserDetailsDto;
import org.reactivestreams.Publisher;

@Controller
@RequiredArgsConstructor
public class RegistrationControllerImpl implements RegistrationController {
    private final RegistrationWebAdapter registrationWebAdapter;

    @Override
    public Publisher<UserDetailsDto> register(RegistrationRq rq) {
        return registrationWebAdapter.register(rq);
    }
}
