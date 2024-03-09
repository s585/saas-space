package name.svetov.authentication.service;

import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.errors.OauthErrorResponseException;
import io.micronaut.security.token.event.RefreshTokenGeneratedEvent;
import io.micronaut.security.token.refresh.RefreshTokenPersistence;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import name.svetov.authentication.repository.OauthRefreshTokenRepository;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import static io.micronaut.security.errors.IssuingAnAccessTokenErrorCode.INVALID_GRANT;

@Singleton
@RequiredArgsConstructor
public class OauthRefreshTokenPersistence implements RefreshTokenPersistence {
    private final OauthRefreshTokenRepository refreshTokenRepository;

    @Override
    public void persistToken(RefreshTokenGeneratedEvent event) {
        if (event != null &&
            event.getRefreshToken() != null &&
            event.getAuthentication() != null &&
            event.getAuthentication().getName() != null) {
            Mono.from(
                refreshTokenRepository.add(
                    event.getAuthentication().getName(),
                    event.getRefreshToken(),
                    false
                )
            ).subscribe();
        }
    }

    @Override
    public Publisher<Authentication> getAuthentication(String refreshToken) {
        return Mono.from(refreshTokenRepository.findByRefreshToken(refreshToken))
            .flatMap(token -> {
                if (token.isRevoked()) {
                    return Mono.error(new OauthErrorResponseException(INVALID_GRANT, "refresh token revoked", null));
                } else {
                    return Mono.just(Authentication.build(token.getUsername()));
                }
            })
            .switchIfEmpty(Mono.error(new OauthErrorResponseException(INVALID_GRANT, "refresh token not found", null)));
    }
}
