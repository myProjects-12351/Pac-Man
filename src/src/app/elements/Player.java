package app.elements;

import app.KeyHandler;

import java.awt.*;
import java.util.List;

public class Player {
    private byte x;
    private byte y;
    private int score;
    private char direction;
    private boolean power;

    public Player(byte x, byte y, char direction, int score, boolean power) {
        this.power = power;
        this.score = score;
        this.direction = direction;
        this.x = x;
        this.y = y;
    }

    public byte getX() {
        return x;
    }

    public void setX(byte x) {
        this.x = x;
    }

    public byte getY() {
        return y;
    }

    public void setY(byte y) {
        this.y = y;
    }

    public void update(List<Wall> walls){
        if(KeyHandler.direction != '\u0000')
            this.direction = KeyHandler.direction;

        switch (direction) {
             case 'r' -> {
                 if(ifCanGo(direction, walls))
                 {
                     x++;
                 }
             }
             case 'l' -> {
                 if(ifCanGo(direction, walls))
                 {
                     x--;
                 }
             }
             case 'u' -> {
                 if(ifCanGo(direction, walls))
                 {
                     y--;
                 }
             }
             case 'd' -> {
                 if (ifCanGo(direction, walls))
                 {
                     y++;
                 }
             }
        }

        System.out.println(this.x + " " + this.y + " " + this.direction);
    }

    public void draw(Graphics2D g2d, byte unitSize){
        g2d.setColor(Color.orange);
        g2d.fillOval(x*unitSize+5, y*unitSize+5, unitSize-10, unitSize-10);
    }

    private boolean ifCanGo(char direction, List<Wall> walls){
        byte x;
        byte y;
        switch (direction)
        {
            case 'r' -> {
                for(short i=0; i<walls.size(); i++){
                    x = walls.get(i).getX();
                    y = walls.get(i).getY();

                    if(this.x+1 == x && this.y==y){
                        return false;
                    }
                }
            }
            case 'l' -> {
                for(short i=0; i<walls.size(); i++){
                    x = walls.get(i).getX();
                    y = walls.get(i).getY();

                    if(this.x-1 == x && this.y==y){
                        return false;
                    }
                }
            }
            case 'u' -> {
                for(short i=0; i<walls.size(); i++){
                    x = walls.get(i).getX();
                    y = walls.get(i).getY();

                    if(this.x == x && this.y-1==y){
                        return false;
                    }
                }
            }
            case 'd' -> {
                for(short i=0; i<walls.size(); i++){
                    x = walls.get(i).getX();
                    y = walls.get(i).getY();

                    if(this.x == x && this.y+1==y){
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
