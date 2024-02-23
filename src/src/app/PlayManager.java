package app;

import app.elements.Emnemie;
import app.elements.Player;
import app.elements.Point;
import app.elements.Wall;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlayManager {
    public boolean isGameOver = false;
    public byte unitSize = 50;
    Player player;

    // walls
    List<Wall> walls = new ArrayList<>();
    // points
    List<Point> points = new ArrayList<>();
    // pac man
    List<Emnemie> emnemies = new ArrayList<>();

    PlayManager(){
        setStart();
    }

    public void draw(Graphics2D g2D){
        for(Wall wall : walls){
            wall.draw(g2D, unitSize);
        }

        player.draw(g2D, unitSize);
    }

    public void update(){
        player.update();
    }

    private void setStart(){
        for(byte i=0; i<GamePanel.BOARD_HEIGHT/unitSize; i++)
        {
            walls.add(new Wall((byte) 0, i));
            walls.add(new Wall(i, (byte) 0));
            walls.add(new Wall(i, (byte) ((GamePanel.BOARD_HEIGHT/unitSize)-1)));
            walls.add(new Wall((byte) (GamePanel.BOARD_HEIGHT/unitSize-1), i));
        }

        for(byte i=0; i<GamePanel.BOARD_HEIGHT/unitSize; i+=2)
        {
            for(byte j=0; j<GamePanel.BOARD_HEIGHT/unitSize; j+=2)
            {
                walls.add(new Wall(i , j));
            }
        }

        player = new Player((byte) 1, (byte) 1);
    }
}
