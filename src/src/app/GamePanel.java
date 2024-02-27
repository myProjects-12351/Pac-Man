package app;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    public static final int BOARD_WIDTH = 950;
    public static final int BOARD_HEIGHT = 950;
    private static final int FPS = 1;

    Thread thread;
    PlayManager playManager;

    GamePanel(){
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        this.setLayout(null);
        this.addKeyListener(new KeyHandler());
        this.setFocusable(true);

        playManager = new PlayManager();
        startGame();
    }

    public void startGame(){
        thread = new Thread(this);
        thread.start();
    }

    public void paintComponent(Graphics g){
        this.removeAll();
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        playManager.draw(g2d);
    }

    @Override
    public void run() {
        double drawInterval = (double) 1_000_000_000 / FPS;  // 1 000 000 000 / fps
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
        if(!playManager.isGameOver && !playManager.isWin && !KeyHandler.pause) {
            playManager.update();
        }
    }
}
