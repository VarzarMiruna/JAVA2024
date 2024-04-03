package Home.Repository;

import Home.Exceptions.BadPathException;
import Home.Records.Document;
import Home.Records.Person;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class DocumentRepository {
    private String directory;
    private Map<Person, List<Document>> documents = new HashMap<>();

    public DocumentRepository(String masterPath) {
        if (!this.pathExistsAndIsDirectory(masterPath)) {
            throw new BadPathException("Given path is not a correct directory.");
        }
        this.directory = masterPath;
        loadDocuments(this.directory, documents);
    }

    private boolean pathExistsAndIsDirectory(String path) {
        Path tmpPath = Paths.get(path);
        return Files.exists(tmpPath) && Files.isDirectory(tmpPath);
    }

    private void loadDocuments(String dirPath, Map<Person, List<Document>> dirContent) {
        File tmpDirectory = new File(dirPath);
        File[] files = tmpDirectory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                loadDocuments(file.getAbsolutePath(), dirContent);
            } else {
                String fileParent = tmpDirectory.getName();
                Person person = new Person( Integer.toString((int)Math.floor(Math.random() *(1000 - 1 + 1) + 1)), fileParent);

                String fileExt = file.getName().substring(file.getName().lastIndexOf(".") + 1);
                Document tmpDoc = new Document(file.getName(), fileExt.toUpperCase());

                List<Document> fileList = new ArrayList<>();
                if (dirContent.containsKey(person)) {
                    fileList.addAll(dirContent.get(person));
                }
                fileList.add(tmpDoc);
                dirContent.put(person, fileList);
            }
        }
    }

    public void listDocuments() {
        for (Map.Entry<Person, List<Document>> entry : this.documents.entrySet()) {
            System.out.println("Person: " + entry.getKey().name());
            System.out.println("Files: ");
            for (Document document : entry.getValue()) {
                System.out.println("\t" + document.name());
            }
            System.out.println("----------------------");
        }
    }

    public List<Document> getDocuments() {
        List<Document> allDocuments = new ArrayList<>();
        for (List<Document> docList : documents.values()) {
            allDocuments.addAll(docList);
        }
        return allDocuments;
    }

//    public void createDocument() {}
//
//    public void updateDocument() {}
}
