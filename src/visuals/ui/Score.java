package visuals.ui;

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

    public void reset(){
        score = 0;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.GREEN);
        g2.drawString("Score: "+score,10, GameLoop.HEIGHT -50);
    }
}
