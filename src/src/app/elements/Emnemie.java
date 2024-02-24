package app.elements;

import java.awt.*;
import java.util.List;

public class Emnemie {
    private byte x;
    private byte y;
    private Color color;

    public Emnemie(byte x, byte y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
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

    }

    public void draw(Graphics2D g2d, byte unitSize){
        g2d.setColor(this.color);
        g2d.fillOval(x*unitSize, y*unitSize, unitSize, unitSize);
    }
}
