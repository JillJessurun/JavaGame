package JavaGame;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Menu extends MouseAdapter {

    private Game game;
    private Handler handler;
    private Random r = new Random();

    public Menu(Game game, Handler handler){
        this.game = game;
        this.handler = handler;
    }

    public void mousePressed(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();

        //play button
        if (mouseOver(mx, my, 310, 250, 350, 100) && game.gameState == Game.STATE.Menu){
            game.gameState = Game.STATE.Game;
            handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
        }

        //quit button
        if (mouseOver(mx, my, 310, 650, 350, 100) && game.gameState == Game.STATE.Menu){
            System.exit(0);
        }

        //options button
        if (mouseOver(mx, my, 310, 450, 350, 100) && game.gameState == Game.STATE.Menu){
            game.gameState = Game.STATE.Options;
        }

        //options back button
        if (mouseOver(mx, my, 310, 250, 350, 100) && game.gameState == Game.STATE.Options){
            game.gameState = Game.STATE.Menu;
        }
    }

    public void mouseRelease(MouseEvent e){

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        if (mx > x && mx < x + width){
            if(my > y && my < y + height){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public void tick(){
        BufferStrategy bs = game.getBufferStrategy();
        Graphics g = bs.getDrawGraphics();
        this.render(g);
    }

    public void render(Graphics g){
        if(game.gameState == Game.STATE.Menu) {
            Font font = new Font("arial", 1, 50);
            Font font2 = new Font("arial", 1, 20);

            g.setFont(font);
            g.setColor(Color.white);
            g.drawString("Menu", 420, 150);

            g.setColor(Color.white);
            g.drawRect(310, 250, 350, 100);
            g.drawString("Play", 430, 310);

            g.setColor(Color.white);
            g.drawRect(310, 450, 350, 100);
            g.drawString("Options", 385, 510);

            g.setColor(Color.white);
            g.drawRect(310, 650, 350, 100);
            g.drawString("Quit", 430, 710);
        }else if(game.gameState == Game.STATE.Options){
            Font font = new Font("arial", 1, 50);
            g.setFont(font);
            g.setColor(Color.white);

            g.drawString("These are the options :)", 420, 150);

            //button go back
            g.setColor(Color.white);
            g.drawRect(310, 250, 350, 100);
            g.drawString("Back", 430, 310);

        }
    }
}
