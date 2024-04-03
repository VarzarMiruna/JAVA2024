package homework;

public class InvalidCatalogException extends Exception{
    public InvalidCatalogException(String s, Exception ex) {
        super("Invalid catalog file.", ex);
    }

    /*public InvalidCatalogException(Exception ex) {
        super("Invalid catalog file.", ex);
    }*/
}