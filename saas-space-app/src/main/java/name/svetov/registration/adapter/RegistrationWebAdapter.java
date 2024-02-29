package name.svetov.registration.adapter;

import name.svetov.registration.dto.RegistrationRq;
import name.svetov.userdetails.dto.UserDetailsDto;
import org.reactivestreams.Publisher;

public interface RegistrationWebAdapter {
    Publisher<UserDetailsDto> register(RegistrationRq rq);
}
