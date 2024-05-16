package compulsory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private final Socket clientSocket;
    private final GameServer server;
    private PrintWriter out;
    private BufferedReader in;

    public ClientThread(Socket clientSocket, GameServer server) {
        this.clientSocket = clientSocket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received input: " + inputLine);
                // am citit comanda
                if (inputLine.equalsIgnoreCase("stop")) {
                    out.println("Stopping server...");
                    server.stop();
                    break;
                } else {
                    out.println("Server received the request: " + inputLine);
                }
            }
            System.out.println("Client disconnected");
        } catch (IOException e) {
            System.out.println("Error handling client input: " + e);
        } finally {
            try {
                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("Error closing client socket: " + e);
            }
        }
    }
    public void sendMessage(String message) {
        out.println(message);
    }
}

