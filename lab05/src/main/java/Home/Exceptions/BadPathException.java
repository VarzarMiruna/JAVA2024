package Home.Exceptions;

public class BadPathException extends RuntimeException {
    public BadPathException() {}

    public BadPathException(String message) {
        super(message);
    }

}
