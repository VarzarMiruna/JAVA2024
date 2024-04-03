package Home.Comands;

import Home.Exceptions.InvalidCatalogException;

public interface Command {
    //void execute() throws CustomException,  InvalidCatalogException;
    void execute(String[] args) throws Exception;
}
