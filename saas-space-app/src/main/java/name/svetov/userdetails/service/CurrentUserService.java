package name.svetov.userdetails.service;

import org.reactivestreams.Publisher;

import java.util.UUID;

public interface CurrentUserService {
    Publisher<UUID> getCurrentUserId();
}
