package name.svetov.registration.adapter;

import name.svetov.registration.dto.RegistrationRq;
import name.svetov.userdetails.dto.UserDetailsDto;

public interface RegistrationWebAdapter {
    UserDetailsDto register(RegistrationRq rq);
}
