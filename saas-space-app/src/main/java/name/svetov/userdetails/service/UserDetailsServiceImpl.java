package name.svetov.userdetails.service;

import io.micronaut.context.annotation.Requires;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import name.svetov.userdetails.model.SearchUserCmd;
import name.svetov.userdetails.model.UserDetails;
import name.svetov.userdetails.repository.UserDetailsRepository;

import java.util.List;
import java.util.UUID;

@Singleton
@Requires(property = "micronaut.application.type", value = "blocking")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;

    @Override
    public UserDetails getOneById(UUID userDetailsId) {
        return userDetailsRepository.getOneById(userDetailsId);
    }

    @Override
    public UserDetails getOneByUsername(String username) {
        return userDetailsRepository.getOneByUsername(username);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userDetailsRepository.existsByUsername(username);
    }

    @Override
    public UserDetails create(UserDetails userDetails) {
        userDetailsRepository.add(userDetails);
        return userDetailsRepository.getOneById(userDetails.getId());
    }

    @Override
    public List<UserDetails> search(SearchUserCmd cmd) {
        return userDetailsRepository.search(cmd);
    }
}
