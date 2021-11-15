package game;
//created by modifying my own lab solutions, as well as using resources such as stackoverflow etc.

import utilities.SoundManager;
import static game.Constants.*;

import java.util.ArrayList;
import java.util.List;

import utilities.JEasyFrame;

public class Game {
    public static final int N_INITIAL_ASTEROIDS = 3;
    public static int life1 = life;
    public static int gameLevel =1;
    public static int ast_clear = 0;
    public static boolean ship_sh;

    public static List<GameObject> objects = new ArrayList<>();
    public static Garbage garbage;
    public static Ship ship;

    public static int score = 0;
    public static boolean title = true;
    public static boolean new_title = false;
    public static boolean game_state = false;
    public static boolean gameOver;
    public static boolean overScreen;

    public static Keys buttons = new Keys();


    public void updateScore(int x){
        score += 10 * x;
    }

    //manages death, updates score, resets game etc.
    private void update(){
        List<GameObject> current = new ArrayList<>();
        int a = 0;
        for(int i = 0; i < objects.size(); i++){
            for(int j = i + 1; j < objects.size(); j++){
                objects.get(i).collisionHandling(objects.get(j));
            }
            if(!objects.get(i).isDead){
                current.add(objects.get(i));
            }
            if(objects.get(i).isDead && objects.get(i) instanceof Ship)
            {
                life1 = life1 - 1;
                if(life1 != 0){
                ship = new Ship(buttons);
                objects.add(ship); }
                else
                {
                    overScreen = true;
                    title = true;
                    SoundManager.play(Constants.menu);
                }
            }
            if(objects.get(i).isDead && objects.get(i) instanceof Asteroid)
                updateScore(4);
            objects.get(i).update();
        }
        synchronized (Game.class){
        objects.clear();
        objects.addAll(current);
        }

        synchronized (Game.class){
            if(Asteroid.asteroids.size()!=0){
            objects.addAll(Asteroid.asteroids);
            Asteroid.asteroids.clear();}
        }

        synchronized (Game.class){
            if(Ship.bullet !=null){
                objects.add(Ship.bullet);
                Ship.bullet =null;
            }
        }

        synchronized (Game.class){
            if(Ship.fBall !=null){
                objects.add(Ship.fBall);
                Ship.fBall =null;
            }
        }

        for(GameObject o : objects){
            if(o instanceof Asteroid)
                a++;
        }

        ast_clear = a;

        if(ast_clear == 0){
            try {
                gameLevel++;
                objects.clear();
                newGame();
                ast_clear++;
                updateScore(20);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    //initial game state
    public Game(){

        for(int i = 0; i < N_INITIAL_ASTEROIDS + gameLevel; i++){
            objects.add(Asteroid.makeRandomAsteroid());
            //objects.add(Garbage.makeRandomGarbage());
            //could not make garbage collecting work :(
        }
        ship = new Ship(buttons);
        objects.add(ship);
    }

    public static Game newGame() throws Exception{
        Game game = new Game();
        return game;
    }

    public static void main(String[] a) throws Exception {
        Game game = newGame();
        View screen = new View(game);
        new JEasyFrame(screen, "1906027").addKeyListener(buttons);
        while(true){
            if(!title){
                game_state=true;
                game.update();
            }
            else{
                game_state=false;
            }
            screen.repaint();
            Thread.sleep(DELAY);
            if(gameOver){
                gameLevel =1;
                objects.clear();
                ast_clear++;
                life1 = life;
                score =0;
                gameOver =false;
                new_title =true;
                game_state=true;
                title =false;
                Ship.fireballScore =0;
                Reset.n =1;
                Reset.life=3;
                newGame();
            }
        }
    }
}
