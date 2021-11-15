package game;
import utilities.Vector2D;

import java.awt.*;
import java.util.Random;

import static game.Constants.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Asteroid extends GameObject {
    public int lvl = 3;
    public static Random random = new Random();

    public static double speed = (Game.gameLevel * 3)/10;
    public static double TOPSPEED = 120 + 50 * speed;

    public static Image asteroid = Constants.ASTEROID;
    public static ArrayList<Asteroid> asteroids = new ArrayList<>();

    public void hit(){
        super.hit();
        int h = random.nextInt(2) + 2;
    }

    public Asteroid(Vector2D position, Vector2D velocity, double RADIUS, int level){
        super(position, velocity, RADIUS);
        this.lvl = level;
    }

    //method that randomises asteroid speed and position
    public static Asteroid makeRandomAsteroid(){
        return new Asteroid(new Vector2D(random.nextInt(SCREEN_WIDTH),
                random.nextInt(SCREEN_HEIGHT)),
                new Vector2D((Math.random()* TOPSPEED),
                        (Math.random()* TOPSPEED)),
                ARADIUS,
                2);
    }

    //method to draw the asteroid on screen
    public void draw(Graphics2D g){
        double asteroidWidth = asteroid.getWidth(null);
        double asteroidHeight = asteroid.getHeight(null);
        AffineTransform transform = new AffineTransform();
        transform.rotate(this.velocity.angle(),0,0);
        transform.scale(this.radius*1/asteroidWidth,this.radius*1/asteroidHeight);
        transform.translate(-asteroidWidth/2.0,-asteroidHeight/2.0);
        AffineTransform transform1 = g.getTransform();
        g.translate(this.position.x, this.position.y);
        g.drawImage(asteroid, transform,null);
        g.setTransform(transform1);
    }

    public void update(){
        super.update();
    }
}

