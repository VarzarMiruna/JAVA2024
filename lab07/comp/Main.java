package comp;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(8); //numerele alese vor fi pana in 8
        game.addPlayer("Player 1");
        game.addPlayer("Player 2");
        game.addPlayer("Player 3");

        game.start();
    }
}