package by.epam.training.changer.exceptions;

public class DuplicateContainsException extends RuntimeException{
    public DuplicateContainsException() {
        super();
    }

    public DuplicateContainsException(String message) {
        super(message);
    }

    public DuplicateContainsException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateContainsException(Throwable cause) {
        super(cause);
    }

    protected DuplicateContainsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
