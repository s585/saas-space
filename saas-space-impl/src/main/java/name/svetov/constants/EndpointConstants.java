package name.svetov.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EndpointConstants {
    public static final String V1 = "/v1";
    public static final String REGISTRATION_ENDPOINT = V1 + "/register";
    public static final String USER_ENDPOINT = V1 + "/user";
}
