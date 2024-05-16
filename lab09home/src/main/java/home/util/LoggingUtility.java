package home.util;

import java.io.IOException;
import java.util.logging.*;

public class LoggingUtility {
    private static Logger logger = null;

    public static Logger getLogger() {
        if (logger == null) {
            logger = Logger.getLogger("JPA Logger");
            logger.setUseParentHandlers(false);

            // Console handler
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.ALL);
            consoleHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(consoleHandler);


            try {
                FileHandler fileHandler = new FileHandler("jpa-operations.log", true);
                fileHandler.setLevel(Level.ALL);
                fileHandler.setFormatter(new SimpleFormatter());
                logger.addHandler(fileHandler);
            } catch (IOException e) {
                logger.log(Level.SEVERE, "File logger not working", e);
            }
        }
        return logger;
    }
}

