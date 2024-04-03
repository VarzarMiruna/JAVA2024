package Home.Main;

import Home.Repository.DocumentRepository;
import Home.Comands.Command;
import Home.Comands.ExportCommand;
import Home.Comands.ReportCommand;
import Home.Comands.ViewCommand;


import Home.Comands.*;
import Home.Repository.DocumentRepository;

public class Main {
    public static void main(String[] args) {
        try {
            DocumentRepository repo = new DocumentRepository(":C/Users/Teo/OneDrive/Desktop/anul_2/sem_2/java/LAB/lab05/ptTema/mierloi.txt");
            Command command;
            switch (args[0]) {
                case "view":
                    command = new ViewCommand();
                    break;
                case "report":
                    command = new ReportCommand(repo);
                    break;
                case "export":
                    command = new ExportCommand(repo);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown command: " + args[0]);
            }
            command.execute(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
