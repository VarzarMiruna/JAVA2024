package home2;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class Tile {
    private final int nr1;
    private final int nr2;

    public Tile(int nr1, int nr2) {
        this.nr1 = nr1;
        this.nr2 = nr2;
    }

    public int getNr1() {
        return nr1;
    }

    public int getNr2() {
        return nr2;
    }

    @Override
    public String toString() {
        return "(" + nr1 + ", " + nr2 + ")";
    }
}

