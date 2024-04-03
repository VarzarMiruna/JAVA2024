package Home.Exceptions;

public class WrongDocTypeException extends RuntimeException{
    public WrongDocTypeException() {}

    public WrongDocTypeException(String message) {
        super(message);
    }

    public WrongDocTypeException(String message, Throwable err) {
        super(message, err);
    }

}
