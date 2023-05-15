package nodes;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class Ship extends Node{
    List<Shape> sprite = new ArrayList<>();
    AffineTransform translate = new AffineTransform();

    public boolean hit(Projectile proj){
        for (int i=0; i<sprite.size(); i++){
            if (sprite.get(i).intersects(proj.getHitbox())){
                return true;
            }
        }
        return false;
    }

    public void addShape(Shape shape){
        sprite.add(shape);
    }

    public void shift(double x, double y){
        setX(getX()+x);
        setY(getY()+y);
        translate.setToTranslation(x,y);
        for (int i=0; i<sprite.size(); i++){
            sprite.set(i,translate.createTransformedShape(sprite.get(i)));
        }
    }

    public void update(){
        super.update();
        if (getxChange()!=0||getyChange()!=0) {
            translate.setToTranslation(getxChange(), getyChange());
            for (int i = 0; i < sprite.size(); i++) {
                sprite.set(i, translate.createTransformedShape(sprite.get(i)));
            }
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        for (Shape shape:sprite){
            g2.fill(shape);
        }
    }
}
