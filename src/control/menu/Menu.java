package control.menu;

import control.Launcher;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * menu, currently only has a start and quit button
 */
public class Menu extends JComponent {
    List<Button> buttons = new ArrayList<>();

    /**
     * creates the menu
     */
    public Menu(){
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,400,400);
        for (Button button:buttons){
            button.draw(g2);
        }
    }
}
