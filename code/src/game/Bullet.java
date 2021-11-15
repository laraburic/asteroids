package game;

import utilities.Vector2D;
import java.awt.*;

import static game.Constants.*;
import java.awt.geom.AffineTransform;

public class Bullet extends GameObject {
    public Vector2D vel;
    public Vector2D dir;
    public Vector2D pos;

    public int secs = 500;

    public static Image bullet_img = BULLET;

    public static double bullet_height = 15;
    public static double bullet_width = 8;
    public static double bullet_radius = (bullet_width + bullet_height)/4;
    public static double bullet_imgWidth;
    public static double bullet_imgHeight;
    public static int bullet_score;

    //bullet initialisation
    public Bullet(Vector2D pos, Vector2D vel, Vector2D dir){
        super(pos, vel, bullet_radius);
        this.pos = pos;
        this.vel = vel;
        this.dir = dir;
    }

    //method that makes the bullets different according to score
    public void update(){
        bullet_score = Game.score;
        pos.addScaled(vel, delay_time);
        pos.wrap(SCREEN_WIDTH, SCREEN_HEIGHT);
        secs--;
        if(secs <= 0){
            isDead = true;
        }

        //if score is under 100, makes bullet smallest etc.
        if(bullet_score <= 100){
            bullet_img = BULLET;
            bullet_width = 8;
            bullet_height = 23;
            Ship.time_delay =40;
            Ship.fireclip = fire1;
        }
        else if(bullet_score > 100 && bullet_score <= 2000) {
            bullet_img = BULLET2;
            bullet_width = 15;
            bullet_height = 25;
            Ship.time_delay = 30;
        }
        else if(bullet_score > 3000 && bullet_score <= 4000) {
            bullet_img = BULLET3;
            bullet_width = 17;
            bullet_height = 27;
            Ship.time_delay = 20;
            Ship.fireclip = fire2;

        }
        else if(bullet_score > 4000) {
            bullet_img = BULLET4;
            bullet_width = 20;
            bullet_height = 30;
            Ship.time_delay = 10;
            Ship.fireclip = fire3;
        }
    }

    //draws bullet on screen
    public void draw(Graphics2D g){
        bullet_imgWidth = bullet_img.getWidth(null);
        bullet_imgHeight = bullet_img.getHeight(null);
        AffineTransform transform = g.getTransform();
        transform.rotate(dir.angle()+Math.PI/2,0,0);
        transform.scale(bullet_width / bullet_imgWidth, bullet_height / bullet_imgHeight);
        transform.translate(-bullet_imgWidth /2.0,-bullet_imgHeight /2.0);
        AffineTransform transform1 = g.getTransform();
        g.translate(pos.x, pos.y);
        g.drawImage(bullet_img,transform,null);
        g.setTransform(transform1);
    }
}
