package name.svetov.userdetails.service;

import name.svetov.userdetails.model.SearchUserCmd;
import name.svetov.userdetails.model.UserDetails;
import org.reactivestreams.Publisher;

import java.util.UUID;

public interface UserDetailsService {
    Publisher<UserDetails> getOneById(UUID userDetailsId);

    Publisher<UserDetails> getOneByUsername(String username);

    Publisher<Boolean> existsByUsername(String username);

    Publisher<UserDetails> create(UserDetails userDetails);

    Publisher<UserDetails> search(SearchUserCmd map);
}
