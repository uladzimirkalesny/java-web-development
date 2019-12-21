package by.epam.training.bank.exception;

public class SettingsMissFieldException extends RuntimeException {
    public SettingsMissFieldException() {
        super();
    }

    public SettingsMissFieldException(String message) {
        super(message);
    }

    public SettingsMissFieldException(String message, Throwable cause) {
        super(message, cause);
    }

    public SettingsMissFieldException(Throwable cause) {
        super(cause);
    }

    protected SettingsMissFieldException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
