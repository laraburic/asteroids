package game;

import utilities.Vector2D;
import static game.Constants.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class fireBall extends GameObject {
    public Vector2D pos;
    public Vector2D vel;
    public Vector2D dir;
    public static double FIREBALL_WIDTH = 100;
    public static double FIREBALL_HEIGHT = 50;
    public static double FRADIUS = (FIREBALL_WIDTH + FIREBALL_HEIGHT)/4;
    public int time = 510;
    public int iterator;
    public static Image fireball;
    public static double fireballWidth;
    public static double fireballHeight;

    //initialises fireball object
    public fireBall(Vector2D pos, Vector2D vel, Vector2D dir){
        super(pos, vel,FRADIUS);
        this.pos = pos;
        this.vel = vel;
        this.dir = dir;
        iterator=0;
    }

    public void update(){
        pos.addScaled(vel, delay_time);
        pos.wrap(SCREEN_WIDTH, SCREEN_HEIGHT);
        time--;
        if(time <= 0) {
            isDead = true;
            fire.stop();
        }
        if(iterator < FIREBALL.size())
            fireball =FIREBALL.get(iterator);
        else if(iterator==FIREBALL.size() && !isDead) {
            iterator=0;
        }
        iterator++;
        fireballWidth = fireball.getWidth(null);
        fireballHeight = fireball.getHeight(null);
    }

    public void draw(Graphics2D g){
            AffineTransform transform = g.getTransform();
            transform.rotate(dir.angle(), 0, 0);
            transform.scale(FIREBALL_WIDTH / fireballWidth, FIREBALL_HEIGHT / fireballHeight);
            transform.translate(-fireballWidth / 2.0, -fireballHeight / 2.0);
            AffineTransform transform1 = g.getTransform();
            g.translate(pos.x, pos.y);
            g.drawImage(fireball, transform, null);
            g.setTransform(transform1);
    }
}


