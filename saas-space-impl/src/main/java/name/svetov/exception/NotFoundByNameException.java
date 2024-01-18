package name.svetov.exception;

import static name.svetov.constants.ErrorConstants.OBJECT_NOT_FOUND_BY_NAME_ERROR_CODE;

public class NotFoundByNameException extends BusinessSaasSpaceException{
    public NotFoundByNameException(String name) {
        super(OBJECT_NOT_FOUND_BY_NAME_ERROR_CODE, "object not found by name '" + name + "'", name);
    }
}
