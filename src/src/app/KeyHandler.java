package app;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public static char direction;
    public static boolean pause = false;

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP -> direction = 'u';
            case KeyEvent.VK_DOWN -> direction = 'd';
            case KeyEvent.VK_LEFT -> direction = 'l';
            case KeyEvent.VK_RIGHT -> direction = 'r';
            case KeyEvent.VK_SPACE -> pause = !pause;
        }
    }

    // do not need
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}
