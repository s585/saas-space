package name.svetov.registration.service;

import name.svetov.registration.model.RegistrationCmd;
import name.svetov.userdetails.model.UserDetails;
import org.reactivestreams.Publisher;

public interface RegistrationService {
    Publisher<UserDetails> register(RegistrationCmd cmd);
}
