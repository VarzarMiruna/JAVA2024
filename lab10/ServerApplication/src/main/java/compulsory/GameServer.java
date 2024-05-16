package compulsory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    private static final int PORT = 8081;

    private ServerSocket serverSocket;

    public GameServer() throws IOException {
        serverSocket = new ServerSocket(PORT);
        System.out.println("Server deschis la portul " + PORT);

        while (true) {
            System.out.println("Asteptam sa se conecteze clienti..");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client conectat");

            ClientThread clientThread = new ClientThread(clientSocket, this);
            clientThread.start();
        }
    }

    public void stop() throws IOException {
        System.out.println("Server is now stopping...");
        serverSocket.close();
        System.exit(0);
    }
}
