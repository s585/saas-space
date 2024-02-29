package name.svetov.authentication.repository;

import io.micronaut.core.annotation.NonNull;
import jakarta.validation.constraints.NotBlank;
import name.svetov.authentication.model.OauthRefreshToken;
import org.reactivestreams.Publisher;

public interface OauthRefreshTokenRepository {
    Publisher<OauthRefreshToken> add(@NonNull @NotBlank String username,
                                     @NonNull @NotBlank String refreshToken,
                                     boolean revoked);

    Publisher<OauthRefreshToken> findByRefreshToken(@NonNull @NotBlank String refreshToken);

    Publisher<Long> updateByUsername(@NonNull @NotBlank String username,
                                     boolean revoked);
}
