package home;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GameServer {
    public static final int PORT = 2434;
    public List<Game> gameList = new ArrayList<>();
    public GameServer() throws IOException{
        try(ServerSocket serverSocket = new ServerSocket(PORT)) {
            while(true){
                System.out.println("Waiting for a client...");
                Socket socket = serverSocket.accept();
                new ClientThread(socket,this).start();
            }
        } catch (IOException e){
            System.err.println("Ooops :( " +e);
        }
    }
    public Game createGame(String name){
        for(Game game:gameList){
            if(game.getName().equals(name))
                return null;
        }
        Game game = new Game(name);
        gameList.add(game);
        return game;
    }
    public Game findGame(String name){
        for(Game game:gameList){
            if(game.getName().equals(name))
                return game;
        }
        return null;
    }
}
