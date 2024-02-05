package name.svetov.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EndpointConstants {
    public static final String V1 = "/v1";
    public static final String REACTIVE = "/reactive";
    public static final String REGISTRATION_ENDPOINT = V1 + "/register";
    public static final String USER_ENDPOINT = V1 + "/user";
    public static final String SEARCH_USER_ENDPOINT = USER_ENDPOINT + "/search";
    public static final String REGISTRATION_REACTIVE_ENDPOINT = REACTIVE + "/register";
    public static final String USER_REACTIVE_ENDPOINT = REACTIVE + "/user";
    public static final String SEARCH_USER_REACTIVE_ENDPOINT = USER_REACTIVE_ENDPOINT + "/search";
}
