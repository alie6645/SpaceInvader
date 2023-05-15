package control;

import nodes.Fleet;
import nodes.GameObject;
import nodes.Projectile;
import nodes.Ship;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameLoop {
    private static List<GameObject> sprites = new ArrayList<>();
    static Ship player = new Ship();
    static Fleet fleet = new Fleet();
    static List<Projectile> missiles = new ArrayList<>();
    static List<Projectile> obstacles = new ArrayList<>();
    public static final int width = 400;
    public static final int height = 400;
    private static final int cd = 10;
    private static final double BULLET_SPEED = 10;
    private static int cdCount = 0;

    public static List<GameObject> getSprites(){
        return sprites;
    }

    public static void loop(){
        for (GameObject sprite:sprites){
            sprite.update();
        }
        checkCollisions();
        fleet.update();
        if (cdCount>=0){
            cdCount--;
        }
    }

    public static void checkCollisions(){
        for (int i=0; i<fleet.ships.size(); i++){
            for (int p=0; p<missiles.size(); p++){
                Ship enemy = fleet.ships.get(i);
                Projectile missile = missiles.get(p);
                if (enemy.hit(missile)){
                    fleet.ships.remove(enemy);
                    sprites.remove(enemy);
                    missiles.remove(missile);
                    sprites.remove(missile);
                    if (fleet.ships.isEmpty()){
                        fleet.createFleet(3,2);
                        sprites.addAll(fleet.ships);
                    }
                }

            }
        }
    }

    public static void shoot(){
        if (cdCount<=0){
            Projectile missile = new Projectile(player.getX(), player.getY(), 0, -BULLET_SPEED);
            sprites.add(missile);
            missiles.add(missile);
            cdCount = cd;
        }
    }

    public static void initialize(){
        player.addShape(new Rectangle(20,20));
        player.shift(100,300);
        sprites.add(player);
        fleet.createFleet(1,2);
        sprites.addAll(fleet.ships);
    }
}
