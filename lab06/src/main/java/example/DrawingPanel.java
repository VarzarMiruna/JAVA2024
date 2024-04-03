package example;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawingPanel extends JPanel {
    final MainFrame frame;
    final static int W = 600, H = 400;
    BufferedImage image;
    Graphics2D graphics;

    // Constructor
    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        initPanel();
    }

    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        clearCanvas();
    }

    // Metodă pentru a curăța canvas-ul (imaginea offscreen)
    private void clearCanvas() {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, W, H);
    }

    // Metodă pentru inițializarea panoului
    private void initPanel() {
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
    }

    // Metodă apelată pentru a crea și desena grila pe panou
    public void createBoard() {
        int gridSizeHorizontal = frame.configPanel.getGridSizeHorizontal();
        int gridSizeVertical = frame.configPanel.getGridSizeVertical();

        clearCanvas(); // Curăță panoul înainte de a desena grila

        // Desenează grila
        graphics.setColor(Color.BLACK);
        // Calculează spațiul dintre liniile grilei
        int cellWidth = W / gridSizeHorizontal;
        int cellHeight = H / gridSizeVertical;

        // Desenează liniile orizontale și verticale ale grilei
        for (int i = 0; i <= gridSizeHorizontal; i++) {
            graphics.drawLine(i * cellWidth, 0, i * cellWidth, H);
        }
        for (int j = 0; j <= gridSizeVertical; j++) {
            graphics.drawLine(0, j * cellHeight, W, j * cellHeight);
        }

        repaint(); // Solicită panoului să se redeseneze
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // Desenează imaginea offscreen pe panou
    }
}
