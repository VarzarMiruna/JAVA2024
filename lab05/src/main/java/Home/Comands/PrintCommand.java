package Home.Comands;

import Home.Comands.Command;
import Home.Repository.DocumentRepository;
import Home.Records.Document;
/**
 * Prints the list of documents on the screen

public class PrintCommand implements Command {
    private DocumentRepository repository;

    public PrintCommand(DocumentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        for (Document doc : repository.getDocuments()) {
            System.out.println(doc.getTitle());
        }
    }

}*/

