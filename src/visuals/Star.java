package visuals;

import control.GameLoop;

import java.awt.*;

public class Star {
    private int x;
    private int y;
    private int xChange;
    private int yChange;
    private int diameter;

    public Star(int x, int y, int diameter){
        this.x = x;
        this.y = y;
        this.diameter = diameter;
    }

    public void setxChange(int xChange) {
        this.xChange = xChange;
    }

    public void setyChange(int yChange){
        this.yChange = yChange;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void update(){
        y+=yChange;
        x+=xChange;
        if (y<0){
            y = GameLoop.HEIGHT;
        }
        if (y>GameLoop.HEIGHT){
            y=0;
        }
        if (x<0){
            x = GameLoop.WIDTH;
        }
        if (x>GameLoop.WIDTH){
            x=0;
        }
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.white);
        g2.fillOval(x,y,diameter,diameter);
    }
}
