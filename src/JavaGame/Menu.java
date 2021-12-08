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
    private String difficulty;

    public Menu(Game game, Handler handler, String difficulty){
        this.game = game;
        this.handler = handler;
        this.difficulty = difficulty;
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

        //options help button
        if (mouseOver(mx, my, 320, 430, 350, 100) && game.gameState == Game.STATE.Options){
            game.gameState = Game.STATE.Help;
        }

        //options button
        if (mouseOver(mx, my, 310, 450, 350, 100) && game.gameState == Game.STATE.Menu){
            game.gameState = Game.STATE.Options;
        }

        //options back button
        if (mouseOver(mx, my, 320, 770, 350, 100) && game.gameState == Game.STATE.Options){
            game.gameState = Game.STATE.Menu;
        }

        //options difficulty button
        if (mouseOver(mx, my, 320, 600, 350, 100) && game.gameState == Game.STATE.Options){
            game.gameState = Game.STATE.Difficulty;
        }

        //help back button
        if (mouseOver(mx, my, 320, 770, 350, 100) && game.gameState == Game.STATE.Help){
            game.gameState = Game.STATE.Options;
        }

        //difficulty back button
        if (mouseOver(mx, my, 320, 770, 350, 100) && game.gameState == Game.STATE.Difficulty){
            game.gameState = Game.STATE.Options;
        }

        //difficulty easy button
        if (mouseOver(mx, my, 320, 120, 350, 100) && game.gameState == Game.STATE.Difficulty){
            game.gameState = Game.STATE.Options;
            difficulty = "easy";
        }

        //difficulty medium button
        if (mouseOver(mx, my, 320, 300, 350, 100) && game.gameState == Game.STATE.Difficulty){
            game.gameState = Game.STATE.Options;
            difficulty = "medium";
        }

        //difficulty hard button
        if (mouseOver(mx, my, 320, 480, 350, 100) && game.gameState == Game.STATE.Difficulty){
            game.gameState = Game.STATE.Options;
            difficulty = "hard";
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
            Font font2 = new Font("arial", 1, 25);

            g.setFont(font);
            g.setColor(Color.white);
            g.drawString("Menu", 415, 150);

            g.setFont(font2);
            g.setColor(Color.white);
            g.drawRect(310, 250, 350, 100);
            g.drawString("Play", 454, 305);

            g.setColor(Color.white);
            g.drawRect(310, 450, 350, 100);
            g.drawString("Options", 435, 510);

            g.setColor(Color.white);
            g.drawRect(310, 650, 350, 100);
            g.drawString("Quit", 454, 705);

        }else if(game.gameState == Game.STATE.Options){
            Font font2 = new Font("arial", 1, 25);
            Font font = new Font("arial", 1, 60);
            g.setFont(font);
            g.setColor(Color.white);

            g.drawString("Options", 369, 130);
            //g.drawString("Use the keys   W A S D   to dodge the enemies!", 215, 190);
            g.setFont(font2);
            if(difficulty.equals("medium")) {
                g.drawString("Current difficulty: " + difficulty, 330, 220);
            }else{
                g.drawString("Current difficulty: " + difficulty, 350, 220);
            }

            //button help
            g.setColor(Color.white);
            g.drawRect(320, 430, 350, 100);
            g.drawString("Help", 460, 488);

            //button difficulty
            g.setColor(Color.white);
            g.drawRect(320, 600, 350, 100);
            g.drawString("Change difficulty", 395, 655);

            //button go back
            g.setColor(Color.white);
            g.drawRect(320, 770, 350, 100);
            g.drawString("Home", 460, 825);

        }else if(game.gameState == Game.STATE.Difficulty){
            Font font2 = new Font("arial", 1, 25);
            g.setFont(font2);
            g.setColor(Color.white);

            //button EASY
            g.setColor(Color.white);
            g.drawRect(320, 120, 350, 100);
            g.drawString("Easy", 459, 180);

            //button MEDIUM
            g.setColor(Color.white);
            g.drawRect(320, 300, 350, 100);
            g.drawString("Medium", 447, 358);

            //button HARD
            g.setColor(Color.white);
            g.drawRect(320, 480, 350, 100);
            g.drawString("Hard", 459, 538);

            //button go back
            g.setColor(Color.white);
            g.drawRect(320, 770, 350, 100);
            g.drawString("Back", 460, 825);
        }else if(game.gameState == Game.STATE.Help){
            Font font2 = new Font("arial", 1, 25);
            g.setFont(font2);
            g.setColor(Color.white);

            //button go back
            g.setColor(Color.white);
            g.drawRect(320, 770, 350, 100);
            g.drawString("Back", 460, 825);
        }

    }
}
