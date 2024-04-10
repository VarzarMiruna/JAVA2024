package comp;


import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Bag b;
    private final List<Player> players = new ArrayList<>();

    //private int PlayerIndex=0;

    public Game(int n) {
        this.b = new Bag(n);
    }

    public void addPlayer(String playerName) {
        players.add(new Player(playerName, this));
    }

    //trb sa returnam player
    public void start() {
        for (Player player : players) {
            new Thread(player).start();
        }
    }

    public Bag getBag() { return b; }

    //isEnded


}
