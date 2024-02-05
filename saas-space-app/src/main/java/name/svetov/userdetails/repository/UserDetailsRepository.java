package name.svetov.userdetails.repository;

import name.svetov.userdetails.model.SearchUserCmd;
import name.svetov.userdetails.model.UserDetails;

import java.util.List;
import java.util.UUID;

public interface UserDetailsRepository {
    UserDetails getOneById(UUID userDetailsId);

    UserDetails getOneByUsername(String username);

    boolean existsByUsername(String username);

    boolean add(UserDetails userDetails);

    List<UserDetails> search(SearchUserCmd cmd);
}
