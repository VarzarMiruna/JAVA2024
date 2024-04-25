package home2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Bag bag;
    private List<Player> players;
    private Player currentPlayer;
    private int currentPlayerIndex = 0;
    private boolean gameOver = false;

    public Game(int n) {
        this.bag = new Bag(n);
        this.players = new ArrayList<>();
    }

    public synchronized boolean isMyTurn(Player player) {
        return currentPlayer == player && !gameOver;
    }

    public synchronized void nextTurn() {
        if (!gameOver) {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            currentPlayer = players.get(currentPlayerIndex);
        }
    }

    public void addPlayer(String playerName) {
        Player player = new Player(playerName, this);
        players.add(player);
        if (players.size() == 1) {
            currentPlayer = player;
        }
    }

    public void play() {
        for (Player player : players) {
            new Thread(player).start();
        }
        startTimer(30000);
    }

    private void startTimer(long timeLimit) {
        Thread timer = new Thread(() -> {
            try {
                Thread.sleep(timeLimit);
                synchronized (this) {
                    gameOver = true;
                    notifyAll();
                    System.out.println("Game over by time limit.");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        timer.setDaemon(true);
        timer.start();
    }

    public synchronized boolean isGameOver() {
        return gameOver;
    }

    public Bag getBag() {
        return bag;
    }

    public static void main(String[] args) {
        Game game = new Game(4);
        game.addPlayer("Player 1");
        game.addPlayer("Player 2");
        game.addPlayer("Player 3");
        game.play();
    }
}

