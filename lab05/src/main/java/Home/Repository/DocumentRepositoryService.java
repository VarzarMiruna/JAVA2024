package Home.Repository;

import Home.Exceptions.InvalidCatalogException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class DocumentRepositoryService {
   /* public void export(DocumentRepository repo, String path)
            throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(
                new File(path),
                repo);
    }
    public DocumentRepository read(String path)
            throws InvalidCatalogException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        DocumentRepository repository = objectMapper.readValue(
                new File(path),
                DocumentRepository.class);
    }*/
}
