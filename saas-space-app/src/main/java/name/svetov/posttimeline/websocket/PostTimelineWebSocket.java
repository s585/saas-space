package name.svetov.posttimeline.websocket;

import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

@Secured(SecurityRule.IS_AUTHENTICATED)
public interface PostTimelineWebSocket {
}
