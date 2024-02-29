package name.svetov.authentication.repository;

import jakarta.inject.Named;
import jakarta.inject.Singleton;
import name.svetov.authentication.model.OauthRefreshToken;
import org.jooq.DSLContext;
import org.jooq.InsertSetMoreStep;
import org.jooq.generated.tables.records.OauthRefreshTokenRecord;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;

import static org.jooq.generated.Tables.OAUTH_REFRESH_TOKEN;

@Singleton
public class OauthRefreshTokenRepositoryImpl implements OauthRefreshTokenRepository {
    private final DSLContext context;

    public OauthRefreshTokenRepositoryImpl(@Named("r2dbc") DSLContext context) {
        this.context = context;
    }

    @Override
    public Publisher<OauthRefreshToken> add(String username, String refreshToken, boolean revoked) {
        return Mono.from(
                insert(username, refreshToken, revoked).returning()
            )
            .map(response -> response.into(OauthRefreshToken.class));
    }

    private InsertSetMoreStep<OauthRefreshTokenRecord> insert(String username, String refreshToken, Boolean revoked) {
        var now = OffsetDateTime.now();
        return context.insertInto(OAUTH_REFRESH_TOKEN)
            .set(OAUTH_REFRESH_TOKEN.USERNAME, username)
            .set(OAUTH_REFRESH_TOKEN.REFRESH_TOKEN, refreshToken)
            .set(OAUTH_REFRESH_TOKEN.REFRESH_TOKEN, refreshToken)
            .set(OAUTH_REFRESH_TOKEN.REVOKED, revoked)
            .set(OAUTH_REFRESH_TOKEN.CREATED_DATE, now)
            .set(OAUTH_REFRESH_TOKEN.UPDATED_DATE, now);
    }

    @Override
    public Publisher<OauthRefreshToken> findByRefreshToken(String refreshToken) {
        return Mono.from(
                context.selectFrom(OAUTH_REFRESH_TOKEN)
                    .where(OAUTH_REFRESH_TOKEN.REFRESH_TOKEN.eq(refreshToken))
            )
            .mapNotNull(response -> response.into(OauthRefreshToken.class));
    }

    @Override
    public Publisher<Long> updateByUsername(String username, boolean revoked) {
        return Mono.from(
                context.update(OAUTH_REFRESH_TOKEN)
                    .set(OAUTH_REFRESH_TOKEN.REVOKED, revoked)
                    .where(OAUTH_REFRESH_TOKEN.USERNAME.eq(username))
            )
            .map(Long::valueOf);
    }
}
