package name.svetov.password.service;

import name.svetov.password.model.Password;
import org.reactivestreams.Publisher;


public interface PasswordService {
    Publisher<Boolean> create(Password password);
}
