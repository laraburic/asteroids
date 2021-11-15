package game;

import utilities.Vector2D;
import java.awt.*;

import static game.Constants.SCREEN_HEIGHT;
import static game.Constants.SCREEN_WIDTH;
import static game.Constants.delay_time;

public abstract class GameObject {
    public Vector2D position;
    public Vector2D velocity;
    public double radius;
    public boolean isDead;

    public abstract void draw(Graphics2D g);

    public GameObject(Vector2D position,Vector2D velocity,double radius){
        this.position = position;
        this.velocity = velocity;
        this.radius = radius;
    }

    public void hit(){
        this.isDead = true;
    }

    //handles object overlapping
    public boolean touch(GameObject other){
        return this.radius + other.radius > this.position.dist(other.position);
    }

    //handles collision in terms of bullets, fireball and ship hitting asteroid
    public void collisionHandling(GameObject other){
        if(this.getClass() != other.getClass() && this.touch(other)){
            if(!((this instanceof  Ship && other instanceof  Bullet)||(this instanceof Bullet && other instanceof Ship))) {
                if(!((this instanceof  Ship && other instanceof  fireBall)||(this instanceof fireBall && other instanceof Ship)))
                    if(!((this instanceof Ship||other instanceof Ship)&& Game.ship_sh)) {
                        if(this instanceof fireBall){
                        other.hit(); }
                        else if(other instanceof fireBall){
                            this.hit();
                        }
                        else{
                            this.hit();
                            other.hit();
                        }
                }
            }
        }
    }

    public void update(){
        position.addScaled(velocity, delay_time);
        position.wrap(SCREEN_WIDTH, SCREEN_HEIGHT);
    }
}
