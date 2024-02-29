package name.svetov.userdetails.service;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import name.svetov.userdetails.model.SearchUserCmd;
import name.svetov.userdetails.model.UserDetails;
import name.svetov.userdetails.repository.UserDetailsRepository;
import org.reactivestreams.Publisher;

import java.util.UUID;

@Singleton
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;

    @Override
    public Publisher<UserDetails> getOneById(UUID userDetailsId) {
        return userDetailsRepository.getOneById(userDetailsId);
    }

    @Override
    public Publisher<UserDetails> getOneByUsername(String username) {
        return userDetailsRepository.getOneByUsername(username);
    }

    @Override
    public Publisher<Boolean> existsByUsername(String username) {
        return userDetailsRepository.existsByUsername(username);
    }

    @Override
    public Publisher<UserDetails> create(UserDetails userDetails) {
        return userDetailsRepository.add(userDetails);
    }

    @Override
    public Publisher<UserDetails> search(SearchUserCmd cmd) {
        return userDetailsRepository.search(cmd);
    }
}
