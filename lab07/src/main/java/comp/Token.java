package comp;

public class Token {
    private final int nr1;
    private final int nr2;

    public Token(int nr1, int nr2) {
        this.nr1 = nr1;
        this.nr2 = nr2;
    }

    public int getnr1() {
        return nr1;
    }

    public int getnr2() {
        return nr2;
    }

    @Override
    public String toString() {
        return "(" + nr1 + ", " + nr2 + ")";
    }
}
