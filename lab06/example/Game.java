package example;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Game extends JPanel implements Serializable {
    private transient BufferedImage image;
    private transient Graphics2D graphics;

    public Game(int width, int height) {
        if (width <= 0) {
            width = 640;
        }
        if (height <= 0) {
            height = 480;
        }
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}
