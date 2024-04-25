package home2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Player implements Runnable {
    private String name;
    private Game game;
    private List<Tile
            > tiles = new ArrayList<>();
    private boolean running = true;

    public Player(String name, Game game) {
        this.name = name;
        this.game = game;
    }

    @Override
    public void run() {
        while (running && !game.isGameOver()) {
            synchronized (game) {
                while (!game.isMyTurn(this) && !game.isGameOver()) {
                    try {
                        game.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                if (game.isGameOver()) {
                    return;
                }
                Tile tile = game.getBag().extractToken();
                if (tile == null) {
                    running = false;
                    game.notifyAll();
                } else {
                    tiles.add(tile);
                    System.out.println(name + " extracted " + tile);
                }
                game.nextTurn();
                game.notifyAll();
            }
        }
    }

    public List<Tile> gettiles() {
        return tiles;
    }

    public String getName() {
        return name;
    }
}
