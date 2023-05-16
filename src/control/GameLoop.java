package control;

import nodes.Fleet;
import nodes.GameObject;
import nodes.Projectile;
import nodes.Ship;
import nodes.ui.Lives;
import nodes.ui.Score;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Static class initializes game objects and processes behavior every frame
 */
public class GameLoop {
    private static List<GameObject> sprites = new ArrayList<>();
    static Ship player = new Ship();
    static Fleet fleet = new Fleet();
    static Score score = new Score();
    static Lives lives = new Lives();
    static List<Projectile> missiles = new ArrayList<>();
    static List<Projectile> obstacles = new ArrayList<>();
    public static final int width = 400;
    public static final int height = 400;
    private static final int cd = 10;
    private static final double BULLET_SPEED = 10;
    private static int cdCount = 0;
    public static int level = 1;
    public static int shots = 1;

    /**
     * gets all sprites currently in use by the game
     * @return list of game objects
     */
    public static List<GameObject> getSprites(){
        return sprites;
    }

    /**
     * called every frame
     */
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

    /**
     * checks enemy-missile, enemy-player, and obstacle-player collisions
     */
    public static void checkCollisions(){
        for (int i=0; i<fleet.ships.size(); i++){
            Ship enemy = fleet.ships.get(i);
            for (int p=0; p<missiles.size(); p++){
                Projectile missile = missiles.get(p);
                if (missile.timeout()){
                    missiles.remove(missile);
                    sprites.remove(missile);
                }
                if (enemy.hit(missile)){
                    destroy(enemy, missile);
                }

            }
            if (player.hit(enemy.getHitbox())){
                hurt();
            }
        }
    }

    /**
     * destroys the specified enemy and missile, called on collision
     * @param enemy enemy to be destroyed
     * @param missile missile that hit
     */
    private static void destroy(Ship enemy, Projectile missile) {
        fleet.ships.remove(enemy);
        sprites.remove(enemy);
        missiles.remove(missile);
        sprites.remove(missile);
        score.addPoints(10*level);
        if (fleet.ships.isEmpty()){
            level++;
            fleet.createFleet(3,5);
            sprites.addAll(fleet.ships);
            fleet.speedUp(level-1);
        }
    }

    /**
     * called on enemy-player collision
     */
    public static void hurt(){

    }

    /**
     * creates a new player projectile
     */
    public static void shoot(){
        if (cdCount<=0){
            for (int i=0; i<shots; i++) {
                Projectile missile = new Projectile(player.getX()+i*10, player.getY(), 0, -BULLET_SPEED);
                sprites.add(missile);
                missiles.add(missile);
            }
            cdCount = cd;
        }
    }

    /**
     * initializes game objects
     */
    public static void initialize(){
        player.addShape(new Rectangle(20,20));
        player.shift(100,300);
        sprites.add(player);
        sprites.add(score);
        sprites.add(lives);
        fleet.createFleet(1,5);
        sprites.addAll(fleet.ships);
    }
}
