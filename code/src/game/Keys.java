package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Keys extends KeyAdapter implements Controller{
    Action action;
    public Keys(){
        action = new Action();
    }

    //movement is controlled using WASD keys, space fires bullets, f fires fireballs,
    //enter starts the game, escape quits the game and r restarts the game once over
    public Action action(){
        return action;
    }
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        switch(key) {
            case KeyEvent.VK_W:
                action.thrust = 1;
                break;
            case KeyEvent.VK_A:
                action.rotate = -1;
                break;
            case KeyEvent.VK_D:
                action.rotate = +1;
                break;
            case KeyEvent.VK_SPACE:
                action.fire = true;
                break;
            case KeyEvent.VK_F:
                action.fshoot = true;
                break;
            case KeyEvent.VK_ENTER:
                if(Game.title &&!Game.gameOver){
                    Game.new_title =true;
                    Game.gameOver =false;
                    Game.title =false;
                }
                break;
            case KeyEvent.VK_ESCAPE:
                if(!Game.game_state &&Game.title){
                    System.exit(0);
                }
                break;
            case KeyEvent.VK_R:
                if(Game.title && Game.new_title){
                    Game.gameOver =true;
                    Game.overScreen=false;
                }
        }
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        switch(key){
            case KeyEvent.VK_W:
                action.thrust = 0;
                break;
            case KeyEvent.VK_A:
                action.rotate = 0;
                break;
            case KeyEvent.VK_D:
                action.rotate = 0;
                break;
            case KeyEvent.VK_SPACE:
                action.fire = false;
                break;
            case KeyEvent.VK_F:
                action.fshoot = false;
        }

    }
}
