package app;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private static final int BOARD_WIDTH = 600;
    private static final int BOARD_HEIGHT = 600;

    GamePanel(){
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        System.out.println(this.getSize());
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
}
