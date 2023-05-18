package control.menu;

import java.awt.*;
import java.awt.event.ActionListener;

/**
 * button for use in menu
 */
public class Button {
    boolean active;
    int x;
    int y;
    int width;
    int height;
    String text;
    Command command;

    /**
     * constructs a button with the given position and size
     * @param x x-coordinate of top left corner
     * @param y y-coordinate of top left corner
     * @param width width
     * @param height height
     */
    public Button(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * sets the button text
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * toggles the button's state between active and inactive
     */
    public void toggle(){
        active = !active;
    }

    public void draw(Graphics2D g2){
        g2.setColor((active)?Color.GREEN:Color.BLACK);
        g2.fillRect(x,y,width,height);
        g2.setColor((active)?Color.BLACK:Color.GREEN);
        g2.drawRect(x,y,width,height);
        g2.drawString(text,x+width/3,y+height/3);
    }

    /**
     * runs the buttons command, called on selection
     */
    public void run(){
        command.run();
    }

    /**
     * adds an action to the button
     * @param command
     */
    public void addCommand(Command command){
        this.command = command;
    }
}
