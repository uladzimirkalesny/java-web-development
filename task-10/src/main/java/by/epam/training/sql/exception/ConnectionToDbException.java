package by.epam.training.sql.exception;

public class ConnectionToDbException extends RuntimeException {
    public ConnectionToDbException() {
        super();
    }

    public ConnectionToDbException(String message) {
        super(message);
    }

    public ConnectionToDbException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionToDbException(Throwable cause) {
        super(cause);
    }

    protected ConnectionToDbException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
