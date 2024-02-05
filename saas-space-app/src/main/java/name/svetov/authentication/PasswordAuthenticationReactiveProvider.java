package name.svetov.authentication;

import io.micronaut.context.annotation.Requires;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import name.svetov.userdetails.service.UserDetailsReactiveService;
import org.reactivestreams.Publisher;
import org.springframework.security.crypto.password.PasswordEncoder;

@Singleton
@Requires(property = "micronaut.application.type", value = "reactive")
@RequiredArgsConstructor
public class PasswordAuthenticationReactiveProvider<T> implements AuthenticationProvider<T> {
    private final UserDetailsReactiveService userDetailsReactiveService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Publisher<AuthenticationResponse> authenticate(T httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
        var identity = String.valueOf(authenticationRequest.getIdentity());
        return userDetailsReactiveService.getOneByUsername(identity)
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
