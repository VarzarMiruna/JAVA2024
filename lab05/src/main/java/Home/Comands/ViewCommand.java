package Home.Comands;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Opens a document using the native operating system application (using Desktop class);
 */

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class ViewCommand implements Command {
    @Override
    public void execute(String[] args) throws Exception {
        if (args.length != 2) {
            throw new IllegalArgumentException("Usage: view <path_to_file>");
        }
        File file = new File(args[1]);
        if (!file.exists()) {
            throw new IOException("File not found: " + args[1]);
        }
        Desktop.getDesktop().open(file);
    }
}


