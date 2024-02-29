package name.svetov.exception;

public class SystemSaasSpaceException extends AbstractSaasSpaceException{
    public SystemSaasSpaceException(String code, String message) {
        super(code, message);
    }

    public SystemSaasSpaceException(String code, String message, Object... params) {
        super(code, message, params);
    }

    public SystemSaasSpaceException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public SystemSaasSpaceException(String code, String message, Throwable cause, Object... params) {
        super(code, message, cause, params);
    }
}
