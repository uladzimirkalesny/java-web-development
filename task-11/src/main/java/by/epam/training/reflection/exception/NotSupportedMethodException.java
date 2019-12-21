package by.epam.training.reflection.exception;

public class NotSupportedMethodException extends RuntimeException {
    public NotSupportedMethodException() {
        super();
    }

    public NotSupportedMethodException(String message) {
        super(message);
    }

    public NotSupportedMethodException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotSupportedMethodException(Throwable cause) {
        super(cause);
    }

    protected NotSupportedMethodException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
