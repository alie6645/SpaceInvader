package nodes;

import control.GameLoop;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Collection of enemy ships for use of fleet consistent behavior
 */
public class Fleet {
    public List<Ship> ships = new ArrayList<>();

    /**
     * gets the ship on the leading edge of the fleet
     * @return the rightmost or leftmost ship
     */
    public Ship getEdge(){
        Ship edge = ships.get(0);
        if (edge.getxChange()>0){
            for (int i=1; i<ships.size(); i++){
                Ship temp = ships.get(i);
                if (temp.getX()>edge.getX()){
                    edge = temp;
                }
            }
        } else {
            for (int i=1; i<ships.size(); i++){
                Ship temp = ships.get(i);
                if (temp.getX()<edge.getX()){
                    edge = temp;
                }
            }
        }
        return edge;
    }

    /**
     * increases the speed of all ships
     * @param num speed increase
     */
    public void speedUp(int num){
        for (int i=0; i<ships.size(); i++) {
            Ship temp = ships.get(i);
            temp.setxChange(temp.getxChange()+num*Math.signum(temp.getxChange()));
        }
    }


    /**
     * called every frame
     */
    public void update(){
        double next = getEdge().getX()+getEdge().getxChange();
        if (next<0 || next> GameLoop.WIDTH -40){
            for (Ship ship:ships){
                ship.shift(0,10);
                ship.setxChange(-ship.getxChange());
            }
        }
    }

    /**
     * shifts all ships
     * @param x
     * @param y
     */
    public void shift(int x, int y){
        for (Ship ship:ships) {
            ship.shift(x, y);
        }
    }

    public Ship random(){
        int index = (int)(Math.random()*ships.size());
        return ships.get(index);
    }

    /**
     * creates a fleet of ships in a grid shape
     * @param rows rows in grid
     * @param cols columns in grid
     */
    public void createFleet(int rows, int cols){
        int colSpace = (GameLoop.WIDTH -100)/cols;
        for (int i=0; i<rows; i++){
            for (int j=0; j<cols; j++){
                Ship temp = new Ship();
                temp.addShape(new Rectangle(20,20));
                temp.addImage("src/alien.png");
                temp.shift(j*colSpace+50,i*50);
                temp.setxChange(2);
                ships.add(temp);
            }
        }
    }
}
