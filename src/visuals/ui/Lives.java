package visuals.ui;

import control.GameLoop;
import nodes.GameObject;

import java.awt.*;

public class Lives implements GameObject {
    int count = 3;
    @Override
    public void update() {

    }

    public void hurt(){
        count--;
    }

    public void reset(){
        count = 3;
    }

    public int getCount(){
        return count;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.GREEN);
        g2.drawString("Lives: " + count, GameLoop.WIDTH -100,GameLoop.HEIGHT -50);
    }
}
