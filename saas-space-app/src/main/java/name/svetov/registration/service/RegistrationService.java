package name.svetov.registration.service;

import name.svetov.registration.model.RegistrationCmd;
import name.svetov.userdetails.model.UserDetails;

public interface RegistrationService {
    UserDetails register(RegistrationCmd cmd);
}
