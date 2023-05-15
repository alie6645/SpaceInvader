package nodes;

import control.GameLoop;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Fleet {
    public List<Ship> ships = new ArrayList<>();

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

    public void update(){
        double next = getEdge().getX()+getEdge().getxChange();
        if (next<0 || next> GameLoop.width-40){
            for (Ship ship:ships){
                ship.shift(0,5);
                ship.setxChange(-ship.getxChange());
            }
        }
        if (ships.size()<5){
            for (Ship ship:ships){
                ship.setxChange(4*Math.signum(ship.getxChange()));
            }
        }
    }

    public void createFleet(int rows, int cols){
        int colSpace = GameLoop.width/cols;
        for (int i=0; i<rows; i++){
            for (int j=0; j<cols; j++){
                Ship temp = new Ship();
                temp.addShape(new Rectangle(20,20));
                temp.shift(j*colSpace,i*50);
                temp.setxChange(2);
                ships.add(temp);
            }
        }
    }
}
