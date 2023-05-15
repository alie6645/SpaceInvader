import control.GameLoop;
import control.Keypad;

import javax.swing.*;

public class Launcher {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(GameLoop.width,GameLoop.height);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(new Keypad());
        GameLoop.initialize();
        Panel panel = new Panel();
        frame.add(panel);
        Timer timer = new Timer(40, e -> {
            GameLoop.loop();
            panel.repaint();
        });

        timer.start();
    }
}
