package app.elements;

import java.awt.*;

public class Wall {
    private byte x;
    private byte y;

    public Wall(byte x, byte y) {
        this.x = x;
        this.y = y;
    }

    public byte getX() {
        return this.x;
    }

    public void setX(byte x) {
        this.x = x;
    }

    public byte getY() {
        return this.y;
    }

    public void setY(byte y) {
        this.y = y;
    }

    public void print(){
        System.out.println("x: " + x + " y: " + y);
    }

    public void draw(Graphics2D g2d, byte unitSize){
        g2d.setColor(new Color(121, 143, 252));
        g2d.fillOval(x*unitSize, y*unitSize, unitSize, unitSize);
    }
}
