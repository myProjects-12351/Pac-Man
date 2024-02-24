package app;

import app.elements.Emnemie;
import app.elements.Player;
import app.elements.Point;
import app.elements.Wall;

import java.awt.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class PlayManager {
    public boolean isGameOver = false;
    public byte unitSize = 50;
    private final byte amountOfEmnemies = 2;
    Player player;
    SecureRandom secureRandom;

    // walls
    public List<Wall> walls = new ArrayList<>();
    // points
    List<Point> points = new ArrayList<>();
    // pac man
    List<Emnemie> emnemies = new ArrayList<>();

    PlayManager(){
        setGame();

        for (int i=0; i< walls.size(); i++){
            walls.get(i).print();
        }

    }

    public void draw(Graphics2D g2D){
        for(Wall wall : walls){
            wall.draw(g2D, unitSize);
        }

        for(Emnemie emnemie : emnemies){
            emnemie.draw(g2D, unitSize);
        }

        player.draw(g2D, unitSize);
    }

    public void update(){
        player.update(walls);

        for(byte i=0; i<emnemies.size(); i++){
            emnemies.get(i).update(walls);
        }
    }

    private void setGame(){
        player = new Player((byte) 1, (byte) 1, 'r');
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
            byte sum = (byte) ((byte) (x + y) % 6);


            switch (sum){
                case 1 -> color = Color.red;
                case 2 -> color = Color.cyan;
                case 3 -> color = Color.green;
                case 4 -> color = new Color(179, 0, 255);
                case 5 -> color = new Color(255, 0, 228);
            }

            emnemies.add(new Emnemie(x, y, color));
        }

    }
}
