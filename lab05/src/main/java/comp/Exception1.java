package comp;

//pt path file
public class Exception1 extends RuntimeException{
    public Exception1(Exception ex) {
        super("Invalid path", ex);
    }
}
