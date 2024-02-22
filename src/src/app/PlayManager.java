package app;

import app.elements.Emnemie;
import app.elements.Point;
import app.elements.Wall;

import java.awt.*;
import java.util.*;
import java.util.List;

public class PlayManager {
    public boolean isGameOver = false;
    public byte unitSize = 50;

    // walls
    List<Wall> walls = new ArrayList<>();
    // points
    List<Point> points = new ArrayList<>();
    // pac man
    List<Emnemie> emnemies = new ArrayList<>();

    PlayManager(){
//        Random random = new Random();

//        while(wallsX.size() <= 1000){
//            wallsX.add((byte)random.nextInt(20));
//            wallsY.add((byte)random.nextInt(20));
//        }
    }

    public void draw(Graphics2D g2D){
        for(short i=0; i<walls.size(); i++){
            g2D.setColor(Color.BLUE);
            g2D.fillRect(walls.get(i).getX()*unitSize, walls.get(i).getY()*unitSize, unitSize, unitSize);
        }
    }

    public void update(){

    }
}
