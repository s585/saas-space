package name.svetov.authentication;

import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import name.svetov.userdetails.service.UserDetailsService;
import org.reactivestreams.Publisher;
import org.springframework.security.crypto.password.PasswordEncoder;

@Singleton
@RequiredArgsConstructor
public class PasswordAuthenticationProvider<T> implements AuthenticationProvider<T> {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Publisher<AuthenticationResponse> authenticate(T httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
        var identity = String.valueOf(authenticationRequest.getIdentity());
        return userDetailsService.getOneByUsername(identity)
            .mapNotNull(userDetails -> {
                if (passwordMatches(authenticationRequest.getSecret(), userDetails.getPassword().getSecret())) {
                    return AuthenticationResponse.success(identity);
                } else {
                    return AuthenticationResponse.exception().getResponse();
                }
            });
    }

    private boolean passwordMatches(Object rawPassword, String encodedPassword) {
        return passwordEncoder.matches(String.valueOf(rawPassword), encodedPassword);
    }
}
