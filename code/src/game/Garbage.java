package game;
//NOT WORKING :(
import utilities.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Random;

import static game.Constants.*;

public class Garbage extends GameObject {
    public static double gSpeed = (Game.gameLevel *3)/10;
    public static double MAX_SPEED = 120+50*gSpeed;
    public static Image gar = Constants.GARBAGE;
    public int gLevel = 3;
    public static Random r = new Random();



    public Garbage(Vector2D position, Vector2D velocity, double RADIUS,int Level){
        super(position,velocity,RADIUS);
        this.gLevel=Level;
    }

    public static Garbage makeRandomGarbage(){
        return new Garbage(new Vector2D(r.nextInt(SCREEN_WIDTH),r.nextInt(SCREEN_HEIGHT)),new Vector2D((Math.random()*MAX_SPEED),(Math.random()*MAX_SPEED)),ARADIUS,2);
    }
    public static ArrayList<Garbage> spawnedGarbage= new ArrayList<>();

    public void hit(){
        super.hit();
        int n= r.nextInt(2)+2;
    }

    public void update(){
        super.update();
    }

    public void draw(Graphics2D g){
        double garW = gar.getWidth(null);
        double garH = gar.getHeight(null);
        AffineTransform t = new AffineTransform();
        t.rotate(this.velocity.angle(),0,0);
        t.scale(this.radius*1/garW,this.radius*1/garH);
        t.translate(-garW/2.0,-garH/2.0);
        AffineTransform t0 = g.getTransform();
        g.translate(this.position.x,this.position.y);
        g.drawImage(gar,t,null);
        g.setTransform(t0);
    }
}

