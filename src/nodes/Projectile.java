package nodes;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * creates a rectangle projectile
 */
public class Projectile extends Node{
    private final int width = 5;
    private final int height = 10;
    private int lifetime = 30;
    private Color base = Color.GREEN;

    /**
     * creates a projectile at the given location, with the given velocity
     * @param x x-coordinate
     * @param y y-coordinate
     * @param xChange x-velocity
     * @param yChange y-velocity
     */
    public Projectile(double x, double y, double xChange, double yChange){
        setX(x);
        setY(y);
        setxChange(xChange);
        setyChange(yChange);
    }

    public Projectile(double x, double y, double xChange, double yChange, Color color){
        setX(x);
        setY(y);
        setxChange(xChange);
        setyChange(yChange);
        base = color;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(base);
        g2.fillRect((int)getX(),(int)getY(),width,height);
        g2.setColor(Color.lightGray);
        g2.fillRect((int)getX()+width/4,(int)getY()+height/4,width/2,height/2);
    }

    /**
     * gets the projectile hitbox
     * @return hitbox
     */
    public Rectangle2D getHitbox(){
        return new Rectangle2D.Double(getX(),getY(),width,height);
    }

    /**
     * checks if the projectile has fizzled
     * @return true if expired, otherwise false
     */
    public boolean timeout(){
        return (lifetime<=0)?true:false;
    }


    /**
     * called every frame
     */
    public void update(){
        super.update();
        lifetime--;
    }
}
