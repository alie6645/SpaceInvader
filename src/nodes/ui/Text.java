package nodes.ui;

import nodes.GameObject;

import java.awt.*;

public class Text implements GameObject {
    String text;
    public Text(String text){
        this.text = text;
    }
    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setFont(new Font("serif",Font.PLAIN,20));
        g2.drawString(text,100, 100);
    }
}
