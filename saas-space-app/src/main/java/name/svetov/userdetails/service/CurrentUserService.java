package name.svetov.userdetails.service;

import name.svetov.userdetails.model.UserDetails;
import org.reactivestreams.Publisher;

public interface CurrentUserService {
    Publisher<UserDetails> getCurrentUser();
}
