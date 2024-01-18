package name.svetov.exception;

import static name.svetov.constants.ErrorConstants.USER_ALREADY_EXISTS_ERROR_CODE;

public class UserAlreadyExistsException extends BusinessSaasSpaceException{
    public UserAlreadyExistsException() {
        super(USER_ALREADY_EXISTS_ERROR_CODE, "user already exists");
    }
}
