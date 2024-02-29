package name.svetov.exception;

import lombok.Getter;
import org.apache.commons.lang3.ArrayUtils;

@Getter
public abstract class AbstractSaasSpaceException extends RuntimeException{
    private final String code;
    private final Object[] params;

    protected AbstractSaasSpaceException(String code, String message) {
        this(code, message, ArrayUtils.EMPTY_OBJECT_ARRAY);
    }

    protected AbstractSaasSpaceException(String code, String message, Object... params) {
        super(message);
        this.code = code;
        this.params = params;
    }

    protected AbstractSaasSpaceException(String code, String message, Throwable cause) {
        this(code, message, cause, ArrayUtils.EMPTY_OBJECT_ARRAY);
    }

    protected AbstractSaasSpaceException(String code, String message, Throwable cause, Object... params) {
        super(message, cause);
        this.code = code;
        this.params = params;
    }
}
