package name.svetov.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EndpointConstants {
    public static final String REGISTRATION_ENDPOINT = "/register";
    public static final String USER_ENDPOINT = "/user";
    public static final String GET_USER_ENDPOINT = USER_ENDPOINT + "/get";
    public static final String SEARCH_USER_ENDPOINT = USER_ENDPOINT + "/search";
    public static final String RELATIONSHIPS_ENDPOINT = "/relationships";
    public static final String FRIEND_ENDPOINT = RELATIONSHIPS_ENDPOINT + "/friend";
    public static final String ADD_FRIEND_ENDPOINT = FRIEND_ENDPOINT + "/add";
    public static final String DELETE_FRIEND_ENDPOINT = FRIEND_ENDPOINT + "/delete";
    public static final String POST_ENDPOINT = "/post";
    public static final String GET_POST_ENDPOINT = POST_ENDPOINT + "/get";
    public static final String CREATE_POST_ENDPOINT = POST_ENDPOINT + "/create";
    public static final String UPDATE_POST_ENDPOINT = POST_ENDPOINT + "/update";
    public static final String DELETE_POST_ENDPOINT = POST_ENDPOINT + "/delete";
    public static final String POST_TIMELINE_ENDPOINT = POST_ENDPOINT + "/timeline";
    public static final String WEB_SOCKET = "/ws";
    public static final String POST_TIMELINE_WEB_SOCKET_ENDPOINT = WEB_SOCKET + "/post/timeline";
}
