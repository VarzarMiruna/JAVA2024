package comp;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String path = "C:\\Users\\Teo\\OneDrive\\Desktop\\anul_2\\sem_2\\java\\Documents\\Miruna_Var";

        var repo = new DocumentManager(path);
        repo.listDocuments();
    }
}

