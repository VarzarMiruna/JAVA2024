package home2;
import java.util.concurrent.TimeUnit;

public class TimeKeeper implements Runnable {

    private final Game game;
    private final int timeLimitSeconds;

    public TimeKeeper(Game game, int timeLimitSeconds) {
        this.game = game;
        this.timeLimitSeconds = timeLimitSeconds;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        while (true) {
            long time = (System.currentTimeMillis() - startTime) / 1000;
            System.out.println("Timp trecut: " + time + " secunde");
            if (time >= timeLimitSeconds) {
                System.out.println("Timpul de extragere a trecut...");
                game.isGameOver();
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
