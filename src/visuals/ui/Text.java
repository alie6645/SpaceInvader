package visuals.ui;

import nodes.GameObject;

import java.awt.*;

public class Text implements GameObject {
    int x = 100;
    int y = 100;
    String text;
    public Text(String text){
        this.text = text;
    }

    public Text(String text, int x, int y){
        this.x = x;
        this.y = y;
        this.text = text;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setFont(new Font("serif",Font.PLAIN,20));
        g2.drawString(text,x, y);
    }
}
