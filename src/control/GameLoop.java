package control;

import nodes.*;
import nodes.ui.Lives;
import nodes.ui.Score;
import nodes.ui.Text;
import visuals.Background;

import javax.swing.*;
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
    static List<Flare> flares = new ArrayList<>();
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;
    private static final int cd = 10;
    private static int enemyCD = 30;
    private static int enemycdCount = enemyCD;
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
        fleet.update();
        if (cdCount>=0){
            cdCount--;
        }
        if (enemycdCount>0){
            enemycdCount--;
        } else {
            Ship source = fleet.random();
            Projectile temp = new Projectile(source.getX(), source.getY(), 0,BULLET_SPEED);
            obstacles.add(temp);
            sprites.add(temp);
            enemycdCount = enemyCD-level*5;
        }
        for (int i=0; i<flares.size(); i++){
            if (flares.get(i).isDone()){
                sprites.remove(flares.get(i));
                flares.remove(i);
                break;
            }
        }
        checkCollisions();
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
                fleet.shift(0,-50);
            }
        }
        for (int i=0; i<obstacles.size(); i++){
            if (player.hit(obstacles.get(i))){
                sprites.remove(obstacles.get(i));
                obstacles.remove(i);
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
        Flare temp = new Flare((int)enemy.getX()+10,(int)enemy.getY()+10);
        flares.add(temp);
        sprites.add(temp);
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
        lives.hurt();
        if (lives.getCount()<=0){
            lose();
        }

    }

    /**
     * called when player has no lives left
     */
    public static void lose(){
        sprites.clear();
        missiles.clear();
        obstacles.clear();
        fleet.ships.clear();
        player.shift(-player.getX(), -player.getY());
        level = 1;
        score.reset();
        lives.reset();
        Launcher.timer.stop();
        sprites.add(new Text("Game Over"));
    }

    /**
     * creates a new player projectile
     */
    public static void shoot(){
        if (cdCount<=0){
            for (int i=0; i<shots; i++) {
                Projectile missile = new Projectile(10+player.getX()+i*10, player.getY(), 0, -BULLET_SPEED, Color.RED);
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
        int[] x = {0,10,20};
        int[] y = {20,0,20};
        player.addShape(new Polygon(x,y,3));
        player.addImage("src/ship.png");
        player.shift(100,300);
        Background back = new Background();
        back.populate(20);
        sprites.add(back);
        sprites.add(player);
        sprites.add(score);
        sprites.add(lives);
        fleet.createFleet(1,5);
        sprites.addAll(fleet.ships);

    }
}
