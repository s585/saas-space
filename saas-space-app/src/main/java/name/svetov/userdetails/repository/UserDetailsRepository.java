package name.svetov.userdetails.repository;

import name.svetov.userdetails.model.SearchUserCmd;
import name.svetov.userdetails.model.UserDetails;
import org.reactivestreams.Publisher;

import java.util.UUID;

public interface UserDetailsRepository {
    Publisher<UserDetails> getOneById(UUID userDetailsId);

    Publisher<UserDetails> getOneByUsername(String username);

    Publisher<Boolean> existsByUsername(String username);

    Publisher<UserDetails> add(UserDetails userDetails);

    Publisher<UserDetails> search(SearchUserCmd cmd);
}
