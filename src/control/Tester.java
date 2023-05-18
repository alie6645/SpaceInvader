package control;

import control.menu.Menu;
import control.menu.MenuKeypad;

import javax.swing.*;

public class Tester {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(400,400);
        Menu menu = new Menu();
        frame.add(menu);
        frame.addKeyListener(new MenuKeypad(menu));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
