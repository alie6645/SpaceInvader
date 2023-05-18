package visuals;

import control.GameLoop;
import nodes.GameObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Background implements GameObject {
    List<Star> stars = new ArrayList<Star>();

    public void update(){
        for (Star star:stars){
            star.update();
        }
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.BLACK);
        g2.fillRect(0,0, GameLoop.WIDTH,GameLoop.HEIGHT);
        for (Star star:stars){
            star.draw(g2);
        }
    }

    public void populate(int num){
        for (int i=0; i<num; i++){
            int x = (int)(Math.random()*GameLoop.WIDTH);
            int y = (int)(Math.random()*GameLoop.HEIGHT);
            int speed = (int)(Math.random()*9)+1;
            int diameter = 7/speed+3;
            Star star = new Star(x,y,diameter);
            star.setyChange(speed);
            stars.add(star);
        }
    }

}
