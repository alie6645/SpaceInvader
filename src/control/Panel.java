package control;

import control.GameLoop;
import nodes.GameObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Panel extends JComponent {
    private static List<GameObject> sprites = GameLoop.getSprites();

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        for (GameObject sprite:sprites){
            sprite.draw(g2);
        }
    }
}
