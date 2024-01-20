package name.svetov.exception;

public class BusinessSaasSpaceException extends AbstractSaasSpaceException{
    public BusinessSaasSpaceException(String code, String message) {
        super(code, message);
    }

    public BusinessSaasSpaceException(String code, String message, Object... params) {
        super(code, message, params);
    }

    public BusinessSaasSpaceException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public BusinessSaasSpaceException(String code, String message, Throwable cause, Object... params) {
        super(code, message, cause, params);
    }
}
