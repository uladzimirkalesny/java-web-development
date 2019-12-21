package by.epam.training.sql.exception;

public class MysqlDriverNotFoundException extends RuntimeException {
    public MysqlDriverNotFoundException() {
        super();
    }

    public MysqlDriverNotFoundException(String message) {
        super(message);
    }

    public MysqlDriverNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MysqlDriverNotFoundException(Throwable cause) {
        super(cause);
    }

    protected MysqlDriverNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
