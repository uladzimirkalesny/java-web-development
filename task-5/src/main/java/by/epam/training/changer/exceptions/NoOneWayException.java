package by.epam.training.changer.exceptions;

public class NoOneWayException extends RuntimeException {
    public NoOneWayException() {
        super();
    }

    public NoOneWayException(String message) {
        super(message);
    }

    public NoOneWayException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoOneWayException(Throwable cause) {
        super(cause);
    }

    protected NoOneWayException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
