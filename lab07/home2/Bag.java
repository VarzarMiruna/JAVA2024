package home2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bag {
    private List<Tile> tiles;

    public Bag(int n) {
        tiles = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j) {
                    tiles.add(new Tile(i, j));
                }
            }
        }
        Collections.shuffle(tiles);
    }

    public synchronized Tile extractToken() {
        if (tiles.isEmpty()) {
            return null;
        }
        return tiles.remove(0);
    }
}

