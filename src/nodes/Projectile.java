package nodes;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Projectile extends Node{
    private final int width = 3;
    private final int height = 10;
    private int lifetime = 20;
    private Color base = Color.RED;

    public Projectile(double x, double y, double xChange, double yChange){
        setX(x);
        setY(y);
        setxChange(xChange);
        setyChange(yChange);
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(base);
        g2.fillRect((int)getX(),(int)getY(),width,height);
    }

    public Rectangle2D getHitbox(){
        return new Rectangle2D.Double(getX(),getY(),width,height);
    }

    public boolean timeout(){
        return (lifetime<=0)?true:false;
    }

    public void update(){
        super.update();
        lifetime--;
    }
}
