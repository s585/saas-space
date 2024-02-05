package name.svetov.password.repository;

import name.svetov.password.model.Password;

public interface PasswordRepository {
    boolean add(Password password);
}
