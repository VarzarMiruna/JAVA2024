package Home.Comands;


//exports the repository to an external file using JSON format; you may use Jackson or other library;

import Home.Exceptions.InvalidCatalogException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Home.Repository.DocumentRepository;

import Home.Exceptions.ReadingError;

import java.io.File;
import java.io.IOException;
/**
 * Exports the repository to an external file using JSON format.
 */

import Home.Repository.DocumentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class ExportCommand implements Command {
    private DocumentRepository repository;

    public ExportCommand(DocumentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Usage: export <output_file_path>");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(args[1]), repository.getDocuments());
    }
}

