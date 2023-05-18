package nodes;

import java.awt.*;

public class Flare implements GameObject{
    int spread = 2;
    int lifetime = 10;
    int frame = 0;
    int x;
    int y;

    public Flare(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public void update() {
        frame++;
    }

    public boolean isDone(){
        return frame>=lifetime;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.yellow);
        for (int i=-1; i<=1; i++){
            for (int j=-1; j<=1; j++){
                int xMod = i*frame*spread;
                int yMod = j*frame*spread;
                g2.fillRect(x+xMod,y+yMod,3,3);
            }
        }
    }
}
