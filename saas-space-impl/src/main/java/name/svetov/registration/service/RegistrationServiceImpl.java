package name.svetov.registration.service;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import name.svetov.password.model.Password;
import name.svetov.password.service.PasswordService;
import name.svetov.registration.model.RegistrationCmd;
import name.svetov.userdetails.model.UserDetails;
import name.svetov.userdetails.service.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Singleton
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final UserDetailsService userDetailsService;
    private final PasswordService passwordService;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Mono<UserDetails> signup(RegistrationCmd cmd) {
        var passwordId = UUID.randomUUID();
        var userDetails = this.buildUserDetails(cmd, passwordId);
        var password = this.buildPassword(cmd, userDetails.getId(), passwordId);
        return passwordService.create(password)
            .then(userDetailsService.create(userDetails));
    }

    private UserDetails buildUserDetails(RegistrationCmd cmd, UUID passwordId) {
        return UserDetails.builder()
            .id(UUID.randomUUID())
            .username(cmd.getUsername())
            .password(
                Password.builder()
                    .id(passwordId)
                    .build()
            )
            .firstName(cmd.getFirstName())
            .lastName(cmd.getLastName())
            .build();
    }

    private Password buildPassword(RegistrationCmd cmd, UUID userDetailsId, UUID passwordId) {
        return Password.builder()
            .id(passwordId)
            .userDetailsId(userDetailsId)
            .secret(passwordEncoder.encode(cmd.getRawPassword()))
            .build();
    }
}
