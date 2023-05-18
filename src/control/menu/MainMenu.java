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
public class MainMenu extends Menu {
    BufferedImage background;

    /**
     * creates the menu
     */
    public MainMenu(){
        setBackground(Color.BLACK);
        Button start = new Button(200,100,100,40);
        Button options = new Button(200,150,100,40);
        Button quit = new Button(200,200,100,40);
        start.setText("Start");
        options.setText("Options");
        quit.setText("Quit");
        start.addCommand(()-> Launcher.startGame());
        options.addCommand(() -> Launcher.openMenu(new OptionMenu()));
        quit.addCommand(()-> System.exit(0));
        buttons.add(start);
        buttons.add(options);
        buttons.add(quit);
        try {
            background = ImageIO.read(new File("src/title.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,400,400);
        g2.drawImage(background,0,0,400,360,null);
        for (Button button:buttons){
            button.draw(g2);
        }
    }
}
