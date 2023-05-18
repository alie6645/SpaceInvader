package control;

import control.menu.MainMenu;
import control.menu.Menu;
import control.menu.MenuKeypad;

import javax.swing.*;

public class Launcher {
    public static JFrame frame;
    public static Timer timer;
    public static int gameSpeed= 40;
    static Panel panel;
    public static void main(String[] args) {
        openMenu(new MainMenu());
        frame.revalidate();
    }

    public static void initialize(){
        frame = new JFrame();
        frame.setSize(GameLoop.WIDTH,GameLoop.HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void openMenu(Menu menu){
        if (frame != null) {
            frame.dispose();
        }
        initialize();
        frame.add(menu);
        frame.addKeyListener(new MenuKeypad(menu));
    }

    public static void startGame(){
        frame.dispose();
        initialize();
        frame.addKeyListener(new Keypad());
        GameLoop.initialize();
        panel = new Panel();
        frame.add(panel);
        timer = new Timer(gameSpeed, e -> {
            GameLoop.loop();
            panel.repaint();
        });
        frame.revalidate();
        timer.start();
        frame.setVisible(true);
    }

    public static void endGame(){
        timer.stop();
        openMenu(new MainMenu());
        frame.revalidate();

    }
}
