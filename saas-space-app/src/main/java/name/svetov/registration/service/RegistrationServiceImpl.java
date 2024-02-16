package name.svetov.registration.service;

import io.micronaut.context.annotation.Requires;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import name.svetov.exception.UserAlreadyExistsException;
import name.svetov.password.model.Password;
import name.svetov.password.service.PasswordService;
import name.svetov.registration.converter.RegistrationConverter;
import name.svetov.registration.model.RegistrationCmd;
import name.svetov.userdetails.model.UserDetails;
import name.svetov.userdetails.service.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Singleton
@Requires(property = "micronaut.application.type", value = "blocking")
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final UserDetailsService userDetailsService;
    private final PasswordService passwordService;
    private final PasswordEncoder passwordEncoder;
    private final RegistrationConverter registrationConverter;

    @Override
    @Transactional
    public UserDetails register(RegistrationCmd cmd) {
        var passwordId = UUID.randomUUID();
        var userDetails = buildUserDetails(cmd, passwordId);
        var password = buildPassword(cmd, userDetails.getId(), passwordId);
        if (userDetailsService.existsByUsername(cmd.getUsername())) {
            throw new UserAlreadyExistsException();
        }
        passwordService.create(password);
        return userDetailsService.create(userDetails);
    }

    private UserDetails buildUserDetails(RegistrationCmd cmd, UUID passwordId) {
        return registrationConverter.convert(cmd).toBuilder()
            .id(UUID.randomUUID())
            .password(
                Password.builder()
                    .id(passwordId)
                    .build()
            )
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
