package example;

import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ConfigPanel extends JPanel {
    JLabel labelHorizontal;
    JLabel labelVertical;
    JTextField gridSizeInputHorizontal;
    JTextField gridSizeInputVertical;
    JButton newGameButton;

    final MainFrame frame;

    private int sizeHorizontal; // Dimensiunea orizontală a grilei
    private int sizeVertical; //Dimensiunea verticală a grilei
    private int[][] stones; //Matrice pentru a stoca pietrele plasate pe tablă



    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        labelHorizontal = new JLabel("Grid size (horizontal):");
        labelVertical = new JLabel("Grid size (vertical):");
        gridSizeInputHorizontal = new JTextField(5);
        gridSizeInputVertical = new JTextField(5);
        newGameButton = new JButton("New Game");

        setLayout(new FlowLayout());
        add(labelHorizontal);
        add(gridSizeInputHorizontal);
        add(labelVertical);
        add(gridSizeInputVertical);
        add(newGameButton);
    }

    // Implement methods to retrieve grid size input for horizontal and vertical dimensions
    public int getGridSizeHorizontal() {
        return Integer.parseInt(gridSizeInputHorizontal.getText());
    }

    public int getGridSizeVertical() {
        return Integer.parseInt(gridSizeInputVertical.getText());
    }

    // Setter pentru gridSizeHorizontal
    public void setGridSizeHorizontal(int gridSizeHorizontal) {
        this.gridSizeInputHorizontal = gridSizeInputHorizontal; // Actualizează variabila de instanță
        gridSizeInputHorizontal.setText(String.valueOf(gridSizeInputHorizontal)); // Actualizează interfața grafică
    }

    // Setter pentru gridSizeVertical
    public void setGridSizeVertical(int gridSizeVertical) {
        this.gridSizeInputVertical = gridSizeInputVertical; // Actualizează variabila de instanță
        gridSizeInputVertical.setText(String.valueOf(gridSizeInputVertical)); // Actualizează interfața grafică
    }
}

