package app;

import app.elements.Emnemie;
import app.elements.Player;
import app.elements.Point;
import app.elements.Wall;

import java.awt.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlayManager {
    public boolean isGameOver = false;
    public boolean isWin = false;
    public byte unitSize = 50;
    private final byte amountOfEmnemies = 10;
    int time=0;
    Player player;
    SecureRandom secureRandom;

    // walls
    public List<Wall> walls = new ArrayList<>();
    // points
    List<Point> points = new ArrayList<>();
    Iterator<Point> pointIterator;
            // pac man
    List<Emnemie> emnemies = new ArrayList<>();
    Iterator<Emnemie> emnemieIterator;

    PlayManager(){
        setGame();
    }

    public void draw(Graphics2D g2D) {
        if (!isGameOver && !isWin && !KeyHandler.pause) {
            for (Wall wall : walls) {
                wall.draw(g2D, unitSize);
            }

            for(Point point : points){
                point.draw(g2D, unitSize);
            }

            for (Emnemie emnemie : emnemies) {
                emnemie.draw(g2D, unitSize);
            }

            player.draw(g2D, unitSize);
        }else if(isGameOver) {
            Font font = new Font("Ink Free", Font.BOLD, 75);
            String[] strings = {"YOU LOST", "Score: "+player.getScore()};
            blackScreen(g2D, strings, Color.red, font, 250, 500);
        } else if (isWin) {
            Font font = new Font("Ink Free", Font.BOLD, 75);
            String[] strings = {"YOU WON" ,"Score: "+player.getScore()};
            blackScreen(g2D, strings, Color.green, font, 250, 500);
        } else if (KeyHandler.pause) {
            Font font = new Font("Ink Free", Font.BOLD, 75);
            String[] strings = {"PAUSE", "Score: "+player.getScore()};
            blackScreen(g2D, strings, Color.yellow, font, 250, 500);
        }
    }

    private void blackScreen(Graphics2D g2D, String[] text, Color color, Font font, int x, int y){
        g2D.setColor(Color.black);
        g2D.fillRect(0,0,1000,1000);
        g2D.setColor(color);
        g2D.setFont(font);

        for(int i=0; i<text.length; i++){
            g2D.drawString(text[i], x, y + (i * font.getSize()));
        }
    }

    public void update(){
        player.update(walls);

        emnemieIterator = emnemies.iterator();
        while (emnemieIterator.hasNext()){
            Emnemie emnemie = emnemieIterator.next();
            emnemie.update(walls);
            if(player.getX() == emnemie.getX() && player.getY() == emnemie.getY()){
                if (player.getSuperPower()){
                    emnemieIterator.remove();
                    player.improveScoreBy(10);
                }else {
                    isGameOver = true;
                }
            }
        }

        pointIterator = points.iterator();
        while (pointIterator.hasNext()) {
            Point point = pointIterator.next();
            if (point.getX() == player.getX() && point.getY() == player.getY()) {
                pointIterator.remove();
                if(point.getPower()){
                    player.setSuperPower(true);
                    time = 0;
                }
                player.improveScoreBy(1);
            }
        }
        if(points.isEmpty()){
            isWin = true;
        }

        if(player.getSuperPower()){
            time++;
            if(time == 10)
                player.setSuperPower(false);
        }
    }

    private void setGame(){
        player = new Player((byte) 1, (byte) 1, 'r', 0, false);
        secureRandom = new SecureRandom();

        byte i;
        for(i=0; i<GamePanel.BOARD_HEIGHT/unitSize; i++)
        {
            walls.add(new Wall((byte) 0, i));
            walls.add(new Wall(i, (byte) 0));
            walls.add(new Wall(i, (byte) ((GamePanel.BOARD_HEIGHT/unitSize)-1)));
            walls.add(new Wall((byte) (GamePanel.BOARD_HEIGHT/unitSize-1), i));
        }

        for(i=2; i<(GamePanel.BOARD_HEIGHT/unitSize)-2; i+=2)
        {
            for(byte j=2; j<(GamePanel.BOARD_HEIGHT/unitSize)-2; j+=2)
            {
                walls.add(new Wall(i , j));
            }
        }

        Color color = Color.red;
        for(i=0; i<amountOfEmnemies; i++){
            byte x = (byte) secureRandom.nextInt(19);
            byte y = (byte) secureRandom.nextInt(19);
            byte sum = (byte) ((byte) (x + y) % 3);


            switch (sum){
                case 0 -> color = Color.red;
                case 1 -> color = Color.cyan;
                case 2 -> color = Color.green;
                case 3 -> color = new Color(179, 0, 255);
                case 4 -> color = new Color(255, 0, 228);
            }

            emnemies.add(new Emnemie(x, y, color));
        }

        boolean flag;
        boolean isPowerPoint;
        for(i = 0; i < (GamePanel.BOARD_HEIGHT / unitSize) - 1; i++) {
            for(byte j = 0; j < (GamePanel.BOARD_WIDTH / unitSize) - 1; j++) {
                flag = true;
                isPowerPoint = false;
                for(Wall wall : walls) {
                    if(wall.getX() == i && wall.getY() == j) {
                        flag = false;
                        break;
                    }
                }
                if(flag) {
                    if(i * j % 50 == 0) isPowerPoint = true;
                    points.add(new Point(i, j, (byte)10, isPowerPoint));
                }
            }
        }

    }
}
