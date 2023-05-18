package nodes;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Ship class used in player and enemy ships
 */
public class Ship extends Node{
    List<Shape> sprite = new ArrayList<>();
    BufferedImage img;
    AffineTransform translate = new AffineTransform();

    /**
     * gets the hitbox of the ship
     * @return hitbox
     */
    public Rectangle2D getHitbox(){
        return sprite.get(0).getBounds2D();
    }

    /**
     * checks if the ship overlaps the given rectangle
     * @param rectangle intersecting rectangle
     * @return true if they intersect, false otherwise
     */
    public boolean hit(Rectangle2D rectangle){
        for (int i=0; i<sprite.size(); i++){
            if (sprite.get(i).intersects(rectangle)){
                return true;
            }
        }
        return false;
    }


    /**
     * checks if the ship is hit by the specified projectile
     * @param proj the projectile
     * @return true if hit, otherwise false
     */
    public boolean hit(Projectile proj){
        return hit(proj.getHitbox());
    }

    /**
     * adds a shape to the ships sprite
     * @param shape the shape to be added
     */
    public void addShape(Shape shape){
        sprite.add(shape);
    }

    /**
     * moves the ship
     * @param x x change
     * @param y y change
     */
    public void shift(double x, double y){
        setX(getX()+x);
        setY(getY()+y);
        translate.setToTranslation(x,y);
        for (int i=0; i<sprite.size(); i++){
            sprite.set(i,translate.createTransformedShape(sprite.get(i)));
        }
    }

    public void addImage(String path){
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * called every frame
     */
    public void update(){
        super.update();
        if (getxChange()!=0||getyChange()!=0) {
            translate.setToTranslation(getxChange(), getyChange());
            for (int i = 0; i < sprite.size(); i++) {
                sprite.set(i, translate.createTransformedShape(sprite.get(i)));
            }
        }
    }

    /**
     * draws the ship
     * @param g2 the graphics it is drawn on
     */
    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.BLUE);
        if (img!=null){
            g2.drawImage(img,(int)getX(),(int)getY(),null);
        } else {
            for (Shape shape:sprite){
                g2.fill(shape);
            }
        }
    }
}
