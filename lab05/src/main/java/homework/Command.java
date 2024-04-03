package homework;

public interface Command {
    void execute() throws BadPathException, IllegalIdException, InvalidCatalogException,
            WritingError, ReadingError, WrongDocTypeException;
    //void execute(String[] args) throws Exception;
}
