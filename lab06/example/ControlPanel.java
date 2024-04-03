package example;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton exitBtn = new JButton("Exit");
    JButton loadBtn = new JButton("Load");
    JButton saveBtn = new JButton("Save");
    JButton resetBtn = new JButton("Reset");

    File filePath = new File("C:/Users/Teo/OneDrive/Desktop/anul_2/sem_2/java/LAB/lab06/savePoza.png");


    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    public void init() {
        //change the default layout manager (just for fun)
        //setLayout(new GridLayout(1, 4));
        add(exitBtn);
        add(loadBtn);
        add(resetBtn);
        add(saveBtn);
        exitBtn.addActionListener(this::exitGame);
        saveBtn.addActionListener(this::saveGame);
        resetBtn.addActionListener(this::resetGame);
        loadBtn.addActionListener(this::loadGame);
    }


    private void loadGame(ActionEvent e) {
        frame.canvas.createBoard();
        try {
            BufferedImage newImage = ImageIO.read(filePath);
            frame.canvas.image = newImage;
            frame.canvas.graphics = frame.canvas.image.createGraphics();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Salvez intr-o imagine aspectul curent al jocului
     *
     * @param e
     */
    private void saveGame(ActionEvent e) {
        try {
            ImageIO.write(frame.canvas.image, "PNG", filePath);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    private void resetGame(ActionEvent e) {
        frame.configPanel.setGridSizeHorizontal(0);
        frame.configPanel.setGridSizeVertical(0);
        frame.canvas.createBoard();
    }


    private void exitGame(ActionEvent e) {
        frame.dispose();
    }
}

