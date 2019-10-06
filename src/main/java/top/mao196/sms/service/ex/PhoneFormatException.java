package top.mao196.sms.service.ex;

public class PhoneFormatException extends CommonException {
    public PhoneFormatException() {
        super();
    }

    public PhoneFormatException(String message) {
        super(message);
    }

    public PhoneFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public PhoneFormatException(Throwable cause) {
        super(cause);
    }

    protected PhoneFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
