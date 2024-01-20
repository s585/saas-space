package name.svetov.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorConstants {
    public static final String OBJECT_NOT_FOUND_BY_ID_ERROR_CODE = "global.object_not_found_by_id_error";
    public static final String OBJECT_NOT_FOUND_BY_NAME_ERROR_CODE = "global.object_not_found_by_name_error";
    public static final String USER_ALREADY_EXISTS_ERROR_CODE = "registration.user_already_exists_error";
}
