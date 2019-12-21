package by.epam.training.library.exception;

public class NoAvailableBookException extends RuntimeException {
    public NoAvailableBookException() {
        super();
    }

    public NoAvailableBookException(String message) {
        super(message);
    }
}
