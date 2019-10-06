package top.mao196.sms.service.ex;

public class ApiInvokeException extends CommonException {
    public ApiInvokeException() {
        super();
    }

    public ApiInvokeException(String message) {
        super(message);
    }

    public ApiInvokeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiInvokeException(Throwable cause) {
        super(cause);
    }

    protected ApiInvokeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
