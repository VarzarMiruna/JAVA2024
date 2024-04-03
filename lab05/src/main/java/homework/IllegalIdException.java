package homework;

public class IllegalIdException extends RuntimeException{
    public IllegalIdException() {}

    public IllegalIdException(String message) {
        super(message);
    }

    public IllegalIdException(String message, Throwable err) {
        super(message, err);
    }

}
