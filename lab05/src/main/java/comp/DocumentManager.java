package comp;


import java.io.File;
import java.util.ArrayList;
import java.util.List;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class DocumentManager {
    private String directory;
    private final Map<Person, List<Document>> documents = new HashMap<>();

    public DocumentManager(String masterPath) {

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

}