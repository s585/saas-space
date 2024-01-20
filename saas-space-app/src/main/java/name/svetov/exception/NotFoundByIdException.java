package name.svetov.exception;

import java.util.UUID;

import static name.svetov.constants.ErrorConstants.OBJECT_NOT_FOUND_BY_ID_ERROR_CODE;

public class NotFoundByIdException extends BusinessSaasSpaceException{
    public NotFoundByIdException(UUID id) {
        super(OBJECT_NOT_FOUND_BY_ID_ERROR_CODE, "object not found by id '" + id + "'", id);
    }
}
