package nodes.ui;

import control.GameLoop;
import nodes.GameObject;

import java.awt.*;

public class Lives implements GameObject {
    int count = 3;
    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.drawString("Lives: " + count, GameLoop.width-100,GameLoop.height-50);
    }
}
