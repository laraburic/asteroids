package game;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import utilities.ImageManager;
import utilities.SoundManager;
import javax.sound.sampled.Clip;

public class Constants {
    public static final int SCREEN_WIDTH = 850;
    public static final int SCREEN_HEIGHT = 500;
    //method incorporating width x height
    public static final Dimension SCREEN_SIZE = new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

    public static final double bullet_speed = 300;
    public static final int life = 5; //initial lives

    public static final double ARADIUS = 50; //asteroid radius

    public static final int DELAY = 10; //in ms
    public static final double delay_time = DELAY/1000.0;

    public static Image ASTEROID, SHIP, BG, BULLET, BULLET2, BULLET3, BULLET4, MENU, GARBAGE;
    public static Clip fire1, fire2, fire3, thrust, menu, fire;
    public static ArrayList<Image> FIREBALL = new ArrayList<>(16);


    static{
        try{
            SHIP = ImageManager.loadImage("ship");
            BULLET = ImageManager.loadImage("bullet1");
            BULLET2 = ImageManager.loadImage("bullet2");
            BULLET3 = ImageManager.loadImage("bullet3");
            BULLET4 = ImageManager.loadImage("bullet4");
            ASTEROID = ImageManager.loadImage("asteroid");
            GARBAGE = ImageManager.loadImage("garbage");
            MENU = ImageManager.loadImage("menu");
            BG = ImageManager.loadImage("bg1");

            //load images in succesion for firing the fireball
            for(int i=1;i<=16;i++){
                String name="fireball"+i;
                FIREBALL.add(ImageManager.loadImage(name));
            }
        }
        catch (IOException e){e.printStackTrace();}
        try{
            fire = SoundManager.getClip("fire");
            fire1 = SoundManager.getClip("fire");
            fire2 = SoundManager.getClip("fire2");
            fire3 = SoundManager.getClip("fire3");
            thrust = SoundManager.getClip("thrust");
            menu = SoundManager.getClip("bangLarge");
        }
        catch(Exception e){
        }
    }

}
