package name.svetov.password.repository;

import name.svetov.password.model.Password;
import org.reactivestreams.Publisher;

public interface PasswordRepository {
    Publisher<Boolean> add(Password password);
}
