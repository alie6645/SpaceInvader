package control;

import control.menu.Menu;
import control.menu.MenuKeypad;

import javax.swing.*;

public class Launcher {
    public static JFrame frame;
    public static Timer timer;
    static Menu menu;
    static Panel panel;
    public static void main(String[] args) {
        frame = new JFrame();
        initialize();
        menu = new Menu();
        frame.add(menu);
        frame.addKeyListener(new MenuKeypad(menu));
        frame.revalidate();
    }

    public static void startGame(){
        frame.dispose();
        frame = new JFrame();
        initialize();
        frame.addKeyListener(new Keypad());
        GameLoop.initialize();
        panel = new Panel();
        frame.add(panel);
        timer = new Timer(40, e -> {
            GameLoop.loop();
            panel.repaint();
        });
        frame.revalidate();
        timer.start();
        frame.setVisible(true);
    }

    public static void initialize(){
        frame.setSize(GameLoop.WIDTH,GameLoop.HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void endGame(){
        frame.dispose();
        frame = new JFrame();
        timer.stop();
        initialize();
        frame.add(menu);
        frame.addKeyListener(new MenuKeypad(menu));
        frame.revalidate();

    }
}
