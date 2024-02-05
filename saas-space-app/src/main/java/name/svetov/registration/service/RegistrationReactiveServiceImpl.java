package name.svetov.registration.service;

import io.micronaut.context.annotation.Requires;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import name.svetov.exception.UserAlreadyExistsException;
import name.svetov.password.model.Password;
import name.svetov.password.service.PasswordReactiveService;
import name.svetov.registration.converter.RegistrationConverter;
import name.svetov.registration.model.RegistrationCmd;
import name.svetov.userdetails.model.UserDetails;
import name.svetov.userdetails.service.UserDetailsReactiveService;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Singleton
@Requires(property = "micronaut.application.type", value = "reactive")
@RequiredArgsConstructor
public class RegistrationReactiveServiceImpl implements RegistrationReactiveService {
    private final UserDetailsReactiveService userDetailsReactiveService;
    private final PasswordReactiveService passwordReactiveService;
    private final PasswordEncoder passwordEncoder;
    private final RegistrationConverter registrationConverter;

    @Override
    @Transactional
    public Mono<UserDetails> register(RegistrationCmd cmd) {
        var passwordId = UUID.randomUUID();
        var userDetails = buildUserDetails(cmd, passwordId);
        var password = buildPassword(cmd, userDetails.getId(), passwordId);
        return Mono.just(cmd)
            .flatMap(item ->
                userDetailsReactiveService.existsByUsername(item.getUsername())
                    .flatMap(user -> {
                        if (BooleanUtils.isTrue(user)) {
                            return Mono.error(new UserAlreadyExistsException());
                        } else {
                            return passwordReactiveService.create(password)
                                .then(userDetailsReactiveService.create(userDetails));
                        }
                    })
            );
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
