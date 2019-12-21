package by.epam.training.ws.exception;

public class UserNotAvailableException extends RuntimeException {
    public UserNotAvailableException() {
        super();
    }

    public UserNotAvailableException(String message) {
        super(message);
    }

    public UserNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotAvailableException(Throwable cause) {
        super(cause);
    }

    protected UserNotAvailableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
