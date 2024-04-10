package example;

import example.MainFrame;
import example.Node;

import javax.swing.*;
import javax.swing.plaf.ScrollPaneUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/*
public class DrawingPanel extends JPanel implements MouseListener {
    final MainFrame frame;
    final static int W = 600, H = 400;
    private int[] x, y;
    BufferedImage image; //the offscreen image
    Graphics2D graphics; //the tools needed to draw in the image

    private int numLinesMari;//nr de linii mari

    private int nrLinii = 0;//alea mici
    private int nrNoduri = 0;//ce pot colora eu

    private ArrayList[] adjacentNodes; //daca doaua noduri sunt adiacente si au o linie mica bold negru si o extramitate e colorate atunci pot sa colorez
    private boolean[][] isNodeColored;//pt a colora nodurile
    private boolean isGameOver;
    private int currentPlayerIndex;

    private List<LineH> linesH = new ArrayList<>();//liniile mele
    private List<LineV> linesV = new ArrayList<>();//liniile mele = new ArrayList<>();//liniile mele
    private List<Node> nodes = new ArrayList<>(); //pietrele pe care pot sa le colorez

    private Color colorOne = Color.red;
    private Color colorTwo = Color.blue;
    private static Color currentPlayerColor;

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        initPanel();
        createBoard();
    }

    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 800, 600);
    }

    //trebuie sa creez nodurile care vor fi in intersectiile liniilor mari
    private void createVertices() {
        int x0 = W / 2;
        int y0 = H / 2; //middle of the board
        int radius = H / 2 - 10; //board radius
        double alpha = 2 * Math.PI / numLinesMari; // the angle
        x = new int[numLinesMari];
        y = new int[numLinesMari];

    }

    final void createBoard() {
        int gridSizeHorizontal = frame.configPanel.getSizeHorizontal();
        int gridSizeVertical = frame.configPanel.getSizeVertical();

        int cellWidth = W / gridSizeHorizontal;
        int cellHeight = H / gridSizeVertical;

        // Desenează liniile orizontale și verticale ale grilei
        for (int i = 0; i <= gridSizeHorizontal; i++) {
            graphics.drawLine(i * cellWidth, 0, i * cellWidth, H);
        }
        for (int j = 0; j <= gridSizeVertical; j++) {
            graphics.drawLine(0, j * cellHeight, W, j * cellHeight);
        }

        createOffscreenImage();
        //createLines();
        createOffscreenImage();
        initComponents();
        drawLines();
        drawnodes();
        addMouseListener(this);
        repaint();
    }

    public void switchTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % 2;
        if (currentPlayerIndex == 0) currentPlayerColor = colorOne;
        else currentPlayerColor = colorTwo;
    }

    public static Color getCurrentPlayerColor() {
        return currentPlayerColor;
    }


    private void initComponents() {
        isGameOver = false;
        currentPlayerIndex = 1;
        currentPlayerColor = colorOne;
        adjacentNodes = new ArrayList[numLinesMari];
        for (int i = 0; i < numLinesMari; i++) {
            adjacentNodes[i] = new ArrayList<>();
        }
        isNodeColored = new boolean[numLinesMari][numLinesMari];
    }


    private void drawLines() {//trbeuie sa desenz liniile mici cu negru bold
        graphics.setColor(Color.BLACK);
        for (int i = 0; i < numLinesMari; i++) {
            graphics.drawOval(x[i], y[i], 10, 10);
            graphics.fillOval(x[i], y[i], 10, 10);
        }
    }

    //gasesc nodurikle adiacente ca sa am posibilitatea de a le colora, ele trbeuie sa aiba o linie bold neagra
    private ArrayList<Integer> findConnectedNodes(int x1, int y1, int x2, int y2) {
        ArrayList<Integer> connectedNodes = new ArrayList<Integer>();
        for (int i = 0; i < nrLinii; i++) { //nrLinii avem liniile mici desenate random
            double distance = Math.abs((y2 - y1) * x[i] - (x2 - x1) * y[i] + x2 * y1 - y2 * x1) / Math.sqrt(Math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2));
            if (distance < 5) {
                connectedNodes.add(i);
            }
        }
        return connectedNodes;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!isGameOver) {
            for (int i = 0; i < nrNoduri - 1; i++) {
                for (int j = i + 1; j < nrNoduri; j++) {
                    int x1 = x[i], y1 = y[i]; // coord un nod
                    int x2 = x[j], y2 = y[j]; // coord al 2 lea nod
                    double distance = Math.abs((y2 - y1) * e.getX() - (x2 - x1) * e.getY() + x2 * y1 - y2 * x1) / Math.sqrt(Math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2));
                    if (distance < 5) {
                        ArrayList<Integer> connectedNodes = findConnectedNodes(x1, y1, x2, y2);
                        System.out.println("Node clicked: " + i + "-" + j);
                        if (adjacentNodes[i].contains(j) && isNodeColored[i][j] == false && isNodeColored[j][i] == false) {
                            if (currentPlayerIndex % 2 == 1) // inseamna ca e player 1
                            {
                                nodes.get(m).setColor(colorOne);
                                graphics.setColor(colorOne);
                                isNodeColored[i][j] = true;
                                isNodeColored[j][i] = true;

                            } else {
                                nodes.get(m).setColor(colorTwo);
                                graphics.setColor(colorTwo);
                                isNodeColored[i][j] = true;
                                isNodeColored[j][i] = true;
                            }
                        }
                        graphics.drawLine(x[i], y[i], x[j], y[j]);
                        repaint();
                        isGameOver();
                        switchTurn();
                    } else System.out.println("Nu pot colora nodul acesta " + i + "si " + j);
                    System.out.println("Connected nodes: " + connectedNodes);
                }
            }
        }
    } else System.out.println("GAME OVER");



}

@Override
public void mousePressed(MouseEvent e) {
}

@Override
public void mouseReleased(MouseEvent e) {
}

@Override
public void mouseEntered(MouseEvent e) {
}

@Override
public void mouseExited(MouseEvent e) {
}

private void drawnodes() {
    graphics.setColor(Color.BLACK);
    for (int i = 0; i < nrNoduri; i++) {
        for (int j = i + 1; j < nrNoduri; j++) {
            if (Math.random() < nrNoduri) {
                graphics.drawLine(x[i], y[i], x[j], y[j]);
                Node nod1 = new Node(x[i], y[i]);
                Node nod2 = new Node(x[j], y[j]);
                Node node = new node(nod1, nod2, false, Color.black);
                nodes.add(nod);
                nrNoduri++;
                adjacentNodes[i].add(j);
                adjacentNodes[j].add(i);
                isNodeColored[i][j] = false;
                isNodeColored[j][i] = false;
            }
        }
    }
}

public boolean isGameOver() {
    // mergem prin toate combinatiile de noduri

    //trebuie ca nodurile colorate sa aiba un bat negru bold si sa aiba o extremitate deja colorata

}

@Override
public void update(Graphics g) {
    paint(g);
}

@Override
protected void paintComponent(Graphics g) {
    g.drawImage(image, 0, 0, this);
    if (isGameOver == true) {
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        String message = "Game Over";
        int x = (getWidth()) / 3;
        int y = (getHeight()) / 2;
        g.drawString(message, x, y);
        String message2;
        if (currentPlayerIndex % 2 == 0) {
            message2 = "Player RED- WINNER";
        } else {
            message2 = "Player BLUE- WINNER";
        }
        g.setFont(new Font("Arial", Font.BOLD, 18));
        int x2 = (getWidth()) / 3;
        int y2 = (getHeight()) / 3;
        g.drawString(message2, x2, y2);
    }
    String message3;
    if (currentPlayerIndex % 2 == 0) {
        message3 = "Player BLUE";
    } else {
        message3 = "Player RED";
    }
    g.setColor(Color.green);
    g.setFont(new Font("Arial", Font.BOLD, 15));
    int x = (getWidth()) / 7;
    int y = (getHeight()) / 7;
    g.drawString(message3, x, y);

}

private void initPanel() {
    setPreferredSize(new Dimension(W, H));
    setBorder(BorderFactory.createEtchedBorder());
}

}*/