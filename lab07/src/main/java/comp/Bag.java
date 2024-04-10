package comp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bag {
    private final List<Token> tokens = Collections.synchronizedList(new ArrayList<>());

    public Bag(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                tokens.add(new Token(i, j));
            }
        }
        Collections.shuffle(tokens);
    }

    // synchronized, ea poate fi accesata de un singur fir de execuție la un moment dat
    // true-> adica celula a fost vizitata, altfel returneaza false
    public synchronized Token extractToken() {
        if (tokens.isEmpty()) {
            return null;
        }
        return tokens.remove(0);
    }

     /*tokens.clear();
            tokens.addAll(explore.getMem().extractTokens(n));
            explore.getMap().matrix[row][col].addAll(tokens);
            try {
        Thread.sleep((long) (Math.random() * 1000));
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
    }*/
}