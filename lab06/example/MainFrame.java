package example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    //DrawingPanel drawingPanel;
    ConfigPanel configPanel;
    ControlPanel controlPanel;
    JFrame frame;

    DrawingPanel canvas;
    
    public MainFrame() {
        frame = new JFrame("Laborator 6");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        init();
    }

    private void init() {
        configPanel = new ConfigPanel(this);
        canvas = new DrawingPanel(this);
        controlPanel = new ControlPanel(this);
        // arrange the components in the container (frame)
        // JFrame uses a BorderLayout by default
        frame.add(canvas, BorderLayout.CENTER);
        frame.add(configPanel, BorderLayout.NORTH);
        frame.add(controlPanel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // invoke the layout manager
        frame.pack();
    }

    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }*/

}
