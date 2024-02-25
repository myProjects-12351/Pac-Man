package app.elements;

import java.awt.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Emnemie {
    private byte x;
    private byte y;
    private char direction;
    SecureRandom secureRandom;
    private Color color;

    boolean rightFlag, leftFlag, upFlag, downFlag;

    public Emnemie(byte x, byte y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;

        secureRandom = new SecureRandom();
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
        direction = randomDirection(direction, walls);
        switch(direction){
            case 'r' -> {
                x++;
            }
            case 'l' -> {
                x--;
            }
            case 'd' -> {
                y++;
            }
            case 'u' -> {
                y--;
            }
        }
    }

    public void draw(Graphics2D g2d, byte unitSize){
        g2d.setColor(this.color);
        g2d.fillOval(x*unitSize, y*unitSize, unitSize, unitSize);
    }

    private char randomDirection(char currDirection, List<Wall> walls){
        rightFlag=false;
        leftFlag=false;
        upFlag=false;
        downFlag=false;

        for(Wall wall : walls){
            if(wall.getY()-1 == this.y && wall.getX() == this.x) {
                downFlag = true;
            }
            if(wall.getY()+1 == this.y && wall.getX() == this.x) {
                upFlag = true;
            }
            if(wall.getX()-1 == this.x  && wall.getY() == this.y) {
                rightFlag = true;
            }
            if(wall.getX()+1 == this.x && wall.getY() == this.y) {
                leftFlag = true;
            }
        }

        List<Character> possibleDirections = new ArrayList<>();
        if(!leftFlag && currDirection!='r') possibleDirections.add('l');
        if(!rightFlag && currDirection!='l') possibleDirections.add('r');
        if(!downFlag && currDirection!='u') possibleDirections.add('d');
        if(!upFlag && currDirection!='d') possibleDirections.add('u');

        //System.out.println(possibleDirections + " " + color + " " + direction + " " + this.x + " " + this.y);

        return possibleDirections.get((byte) secureRandom.nextInt(possibleDirections.size()));
    }
}
