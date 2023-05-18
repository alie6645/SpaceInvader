package control;

import nodes.Ship;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Keypad extends KeyAdapter {
    Ship player = GameLoop.player;
    final double SPEED = 3;
    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyChar()){
            case 'a':
                player.setxChange(-SPEED);
                break;
            case 'd':
                player.setxChange(SPEED);
                break;
            case 'b':
                if (Launcher.timer.isRunning()){
                    Launcher.timer.stop();
                } else {
                    Launcher.timer.start();
                }
                break;
            case ' ':
                GameLoop.shoot();
                if (!Launcher.timer.isRunning()){
                    GameLoop.reset();
                    Launcher.endGame();
                }
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
