package game;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class View extends JComponent {

    public final int WIDTH = Constants.SCREEN_WIDTH;
    public final int HEIGHT = Constants.SCREEN_HEIGHT;
    public int GWIDTH = 400;
    public int GHEIGHT = 250;

    public Image bg = Constants.BG;
    public Image menu = Constants.MENU;

    AffineTransform transformBG;
    AffineTransform transformIMG;

    private Game game;

    @Override
    public Dimension getPreferredSize(){
        return Constants.SCREEN_SIZE;
    }

    @Override
    public void paintComponent(Graphics g0){
        Graphics2D g = (Graphics2D) g0;
        if(Game.overScreen && Game.title){
            g.drawImage(bg, transformBG,null);
            transformIMG = new AffineTransform();
            double gWidth = menu.getWidth(null);
            double gHeight = menu.getHeight(null);
            transformIMG.scale(GWIDTH/gWidth,GHEIGHT/gHeight);
            g.translate(WIDTH/2-GWIDTH/2,HEIGHT/2-GHEIGHT);
            g.drawImage(menu, transformIMG,null);
            g.setColor(Color.BLUE);
            g.setFont(new Font("Helvetica",Font.BOLD,25));
            g.drawString("PRESS 'R' TO RESTART THE GAME",-150,400);
            g.setColor(Color.BLUE);
            g.drawString("SCORE: " + Game.score,150,300);
            g.setColor(Color.RED);
            g.drawString("PRESS 'ESCAPE' TO QUIT THE GAME",300,400);
        } else if(Game.title && !Game.new_title){
            g.drawImage(bg, transformBG,null);
            g.setColor(Color.BLUE);
            g.setFont(new Font("Helvetica", Font.ITALIC,25));
            g.drawString("PRESS 'ENTER' TO START",WIDTH/2-150,HEIGHT/2-30);
            g.drawString("PRESS 'ESCAPE' TO QUIT",WIDTH/2-150,HEIGHT/2+30);
        }

        else if(Game.game_state){
        g.drawImage(bg, transformBG,null);

        synchronized (Game.class){
        for(GameObject object : Game.objects) {
            object.draw(g);
            }
        }

        g.setColor(Color.CYAN);
        g.setFont(new Font("Helvetica",Font.BOLD,20));
        g.drawString("HEARTS: "+ (Game.life1),WIDTH/2-50,20);
        g.drawString("Fireballs: "+ Ship.fireballScore,0,HEIGHT-5);
        g.drawString("SCORE: "+Game.score,0,20); }
    }

    public View(Game game){
        this.game = game;
        double widthIMG = bg.getWidth(null);
        double heightIMG = bg.getHeight(null);
        double stretch_x = (widthIMG > WIDTH? 1 : WIDTH/widthIMG);
        double stretch_y = (heightIMG > HEIGHT? 1 : HEIGHT/heightIMG);
        transformBG = new AffineTransform();
        transformBG.scale(stretch_x,stretch_y);
    }
}
