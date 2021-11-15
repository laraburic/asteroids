package game;

import static game.Constants.*;

import java.awt.*;
import java.awt.geom.AffineTransform;

import utilities.SoundManager;
import utilities.Vector2D;

import javax.sound.sampled.Clip;


public class Ship extends GameObject {
    public static final double ROTATION_RATE = 2*Math.PI; //the rate at which ship rotates
    public static final double THRUST_RATE = 200;
    public static final double SPEED_DELAY = 0.01; //delay during ship movement

    public Vector2D dir = new Vector2D(0,-1);

    public static Bullet bullet;
    private Controller controller;

    public static Image ship = Constants.SHIP;
    public double ship_imgWidth = ship.getWidth(null);
    public double ship_imgHeight = ship.getHeight(null);
    public static final double WIDTH = 60;
    public static final double HEIGHT = 60;
    public static final double RADIUS = (WIDTH + HEIGHT) / 4;

    public int time1;
    public int time2;
    public int time3;
    public static int time_delay = 40;

    //fireball:
    public static fireBall fBall;
    public static int fireballScore;
    public static Clip fireclip = fire1;

    //sets up the bullet object
    private void makeBullet(){
        bullet = new Bullet(new Vector2D(position), new Vector2D(velocity), new Vector2D(dir));
        bullet.pos.add(new Vector2D(25,0).rotate(dir.angle()));
        bullet.vel.normalise().addScaled(dir,50 * bullet_speed * delay_time);
    }

    //sets fireball object
    private void fireBall(){
        fBall = new fireBall(new Vector2D(position),new Vector2D(velocity),new Vector2D(dir));
        fBall.pos.add(new Vector2D(25,0).rotate(dir.angle()));
        fBall.vel.normalise().addScaled(dir,65* bullet_speed * delay_time);
    }


    public Ship(Controller controller){
        super(new Vector2D(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2), new Vector2D(0,0), RADIUS);
        this.controller = controller;
        time1 = 500;
    }

    public void update(){
        time2++;
        time3++;
        Action action = controller.action();
        dir.rotate(ROTATION_RATE * delay_time * action.rotate);
        if(action.thrust==1 && velocity.mag()<200) {
            velocity.addScaled(dir, THRUST_RATE * delay_time);
            thrust.loop(-1);
        }
        else {
            velocity.subtract(new Vector2D(velocity).mult(SPEED_DELAY));
            thrust.stop();
        }
        position.addScaled(velocity, delay_time);
        position.wrap(SCREEN_WIDTH, SCREEN_HEIGHT);

        if(action.fire && time2 >= time_delay){
            makeBullet();
            SoundManager.play(fireclip);
            action.fire = false;
            time2=0;
        }
        time1--;
        if(Game.score /(Reset.n *1000)>0) {
            fireballScore++;
            Reset.n++;
        }
        if(action.fshoot && fireballScore >=1 && time3>= time_delay *10){
            fireBall();
            SoundManager.play(fire);
            action.fshoot = false;
            time3 = 0;
            fireballScore--;
        }
    }

    public void draw(Graphics2D g){
        AffineTransform transform = new AffineTransform();
        transform.rotate(dir.angle()+Math.PI/2,0,0);
        transform.scale(WIDTH/ ship_imgWidth,HEIGHT/ ship_imgHeight);
        transform.translate(-ship_imgWidth /2.0,-ship_imgHeight /2.0);
        AffineTransform transform1 = g.getTransform();
        g.translate(position.x, position.y);
        g.drawImage(ship, transform,null);
        g.setTransform(transform1);
        g.setColor(Color.white);

    }

}