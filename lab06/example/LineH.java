package example;


public class LineH {
    private int y; // Poziția pe axa Y a liniei orizontale
    private int startX; // Punctul de început al liniei pe axa X
    private int endX; // Punctul de sfârșit al liniei pe axa X

    public LineH(int y, int startX, int endX) {
        this.y = y;
        this.startX = startX;
        this.endX = endX;
    }

    // Getteri și Setteri
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }
}

