package nodes.ui;

import control.GameLoop;
import nodes.GameObject;

import java.awt.*;

public class Score implements GameObject {
    int score = 0;

    @Override
    public void update() {

    }

    public void addPoints(int val){
        score += val;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.drawString("Score: "+score,10, GameLoop.height-50);
    }
}
