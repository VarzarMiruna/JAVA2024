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
    private int sizeHorizontal; // dimensiunea orizontala a grilei
    private int sizeVertical; //dimensiunea verticala a grilei
    private int[][] stones; //matricea pt a stoca nodurile


    MainFrame frame;
    JButton createButton;

    JLabel labelHorizontal;
    JLabel labelVertical;

    JSpinner spinnerHorizontal;
    JSpinner spinnerVertical;


    public int getSizeHorizontal() {
        return sizeHorizontal;
    }
    public int getSizeVertical() {
        return sizeVertical;
    }

    public void setSizeHorizontal(int sizeHorizontal) {
        this.sizeHorizontal = sizeHorizontal;
    }
    public void setSizeVertical(int sizeVertical) {
        this.sizeVertical = sizeVertical;
    }

    //gettere si settere pentru spinnere
    public JSpinner getSpinnerHorizontal() {
        return spinnerHorizontal;
    }

    public JSpinner getSpinnerVertical() {
        return spinnerVertical;
    }

    public void setSpinnerVertical(JSpinner spinnerVertical) {
        this.spinnerVertical = spinnerVertical;
    }

    public void setSpinnerHorizontal(JSpinner spinnerHorizontal) {
        this.spinnerHorizontal = spinnerHorizontal;
    }


    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        //create the label and the spinner
        labelHorizontal = new JLabel("Nr linii Orizontale: ");
        spinnerHorizontal = new JSpinner(new SpinnerNumberModel(3, 2, 100, 1));
        spinnerHorizontal.addChangeListener(e -> {
            sizeHorizontal = (Integer) spinnerHorizontal.getValue();
            frame.canvas.createBoard();
        });

        add(labelHorizontal);
        add(spinnerHorizontal);

        labelVertical = new JLabel("Nr linii Verticale: ");
        spinnerVertical = new JSpinner(new SpinnerNumberModel(3, 2, 100, 1));
        spinnerVertical.addChangeListener(e -> {
            sizeVertical = (Integer) spinnerVertical.getValue();
            frame.canvas.createBoard();
        });

        add(labelVertical);
        add(spinnerVertical);
    }


}

