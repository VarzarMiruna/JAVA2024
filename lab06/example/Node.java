package example;

import java.awt.*;
public class Node {
    private LineH l1;
    private LineV l2;
    private boolean colored;
    private Color color;

    public Node(LineH l1, LineV l2, boolean colored, Color color) {
        this.l1 = l1;
        this.l2 = l2;
        this.colored = colored;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public LineH getL1() {
        return l1;
    }

    public void setL1(LineH l1) {
        this.l1 = l1;
    }

    public LineV getL2() {
        return l2;
    }

    public void setL2(LineV l2) {
        this.l2 = l2;
    }

    public boolean isColored() {
        return colored;
    }

    public void setColored(boolean colored) {
        this.colored = colored;
    }
}

