package control;

import nodes.Ship;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Keypad extends KeyAdapter {
    Ship player = GameLoop.player;
    final double SPEED = 2;
    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyChar()){
            case 'a':
                player.setxChange(-SPEED);
                break;
            case 'd':
                player.setxChange(SPEED);
                break;
            case ' ':GameLoop.shoot();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyChar()){
            case 'a':
                if ((player.getxChange() < 0)) {
                    player.setxChange(0);
                }
            case 'd':
                if ((player.getxChange() > 0)) {
                    player.setxChange(0);
                }
        }
    }
}
