package home;


import static java.awt.BorderLayout.CENTER;
import javax.swing.JFrame;

public class MainFrame extends JFrame{
    Board board;
    public MainFrame(){
        board = new Board(this);
        add(board,CENTER);
        pack();
    }
}
