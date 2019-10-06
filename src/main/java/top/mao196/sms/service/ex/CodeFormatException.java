package top.mao196.sms.service.ex;

public class CodeFormatException extends CommonException {
    public CodeFormatException() {
        super();
    }

    public CodeFormatException(String message) {
        super(message);
    }

    public CodeFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public CodeFormatException(Throwable cause) {
        super(cause);
    }

    protected CodeFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
