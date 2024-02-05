package name.svetov.userdetails.service;

import name.svetov.userdetails.model.SearchUserCmd;
import name.svetov.userdetails.model.UserDetails;

import java.util.List;
import java.util.UUID;

public interface UserDetailsService {
    UserDetails getOneById(UUID userDetailsId);

    UserDetails getOneByUsername(String username);

    boolean existsByUsername(String username);

    UserDetails create(UserDetails userDetails);

    List<UserDetails> search(SearchUserCmd map);
}
