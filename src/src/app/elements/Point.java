package app.elements;

import java.awt.*;

public class Point {
    private byte x;
    private byte y;
    private byte pointSize;

    public Point(byte x, byte y, byte pointSize) {
        this.x = x;
        this.y = y;
        this.pointSize = pointSize;
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

    public void draw(Graphics2D g2d, byte unitSize){
        g2d.setColor(Color.white);
        g2d.fillOval(this.x*unitSize + unitSize/2-pointSize/2, this.y*unitSize+unitSize/2-pointSize/2,pointSize,pointSize);
    }

    public void print(){
        System.out.println("x: " + x + " y: " + y);
    }

}
