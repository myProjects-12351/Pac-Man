package app;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    private static final int BOARD_WIDTH = 1000;
    private static final int BOARD_HEIGHT = 1000;
    private static final int FPS = 60;

    Thread thread;
    PlayManager playManager;

    GamePanel(){
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));

        playManager = new PlayManager();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        playManager.draw(g2d);
    }

    @Override
    public void run() {
        double drawInterval = 1_000_000_000 / FPS;  // 1 000 000 000 / fps
        double delta = 0;
        long lastTime = System.nanoTime();
        long currTime;

        while(thread != null){
            currTime = System.nanoTime();

            delta += (currTime - lastTime) / drawInterval;
            lastTime = currTime;

            if(delta > 1){
                update();
                repaint();
                delta--;
            }
        }
    }

    private void update(){
        if(!KeyHandler.pause && !playManager.isGameOver) {
            playManager.update();
        }
    }
}
