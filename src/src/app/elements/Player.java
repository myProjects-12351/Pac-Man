package app.elements;

import app.KeyHandler;

import java.awt.*;

public class Player {
    private byte x;
    private byte y;
    private char direction;
    private boolean power;

    public Player(byte x, byte y) {
        this.power = false;
        this.direction = 'r';
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

    public void update(){
        switch (KeyHandler.direction){
             case 'r' -> {
                 x++;
             }
             case 'l' -> {
                 x--;
             }
             case 'u' -> {
                 y--;
             }
             case 'd' -> {
                 y++;
             }
        }
    }

    public void draw(Graphics2D g2d, byte unitSize){
        g2d.setColor(Color.orange);
        g2d.fillOval(x*unitSize+5, y*unitSize+5, unitSize-10, unitSize-10);
    }
}
