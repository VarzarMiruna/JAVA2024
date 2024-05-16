package compulsory;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8081;

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private BufferedReader consoleInput;

    public void start() throws IOException {
        consoleInput = new BufferedReader(new InputStreamReader(System.in));
        socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        System.out.println("Conectat la server");

        String inputLine;
        while ((inputLine = consoleInput.readLine()) != null) {
            out.println(inputLine);

            if (inputLine.equalsIgnoreCase("exit")) {
                break; // exit daca primim exit
            }
            System.out.println(in.readLine());// raspunsul de la server
        }

        System.out.println("Deconectat de la server");
        in.close();
        out.close();
        socket.close();
    }
}
