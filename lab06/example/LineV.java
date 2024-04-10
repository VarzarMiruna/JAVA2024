package example;

public class LineV {
    private int x; // Poziția pe axa X a liniei verticale
    private int startY; // Punctul de început al liniei pe axa Y
    private int endY; // Punctul de sfârșit al liniei pe axa Y

    public LineV(int x, int startY, int endY) {
        this.x = x;
        this.startY = startY;
        this.endY = endY;
    }

    // Getteri și Setteri
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }
}

