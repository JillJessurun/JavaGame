package JavaGame;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.io.*;
import java.util.Random;

public class Menu extends MouseAdapter {

    private Game game;
    private Handler handler;
    private Random r = new Random();
    private String difficulty;
    private HUD hud;
    File file;
    BufferedReader bufferedReader;
    private boolean appended = false;
    private boolean newHighscore = true;

    public Menu(Game game, Handler handler, String difficulty, HUD hud, File file, BufferedReader bufferedReader) {
        this.file = file;
        this.bufferedReader = bufferedReader;
        this.game = game;
        this.handler = handler;
        this.difficulty = difficulty;
        this.hud = hud;
    }

    public void mousePressed(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();

        //play button
        if (mouseOver(mx, my, 310, 430, 350, 100) && game.gameState == Game.STATE.Menu){
            game.gameState = Game.STATE.Game;
            handler.clearEnemies();
            handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
        }

        //quit button
        if (mouseOver(mx, my, 310, 770, 350, 100) && game.gameState == Game.STATE.Menu){
            System.exit(0);
        }

        //options difficulty button
        if (mouseOver(mx, my, 310, 600, 350, 100) && game.gameState == Game.STATE.Options){
            game.gameState = Game.STATE.Difficulty;
        }

        //options button
        if (mouseOver(mx, my, 310, 600, 350, 100) && game.gameState == Game.STATE.Menu){
            handler.clearEnemies();
            game.gameState = Game.STATE.Options;
        }

        //options help button
        if (mouseOver(mx, my, 310, 430, 350, 100) && game.gameState == Game.STATE.Options){
            game.gameState = Game.STATE.Help;
        }

        //options back button
        if (mouseOver(mx, my, 310, 770, 350, 100) && game.gameState == Game.STATE.Options){
            for (int i = 0; i < 15; i++) {
                handler.addObject(new MenuEffect(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.MenuEffect, handler));
            }
            game.gameState = Game.STATE.Menu;
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

        //gameover home button
        if (mouseOver(mx, my, 310, 770, 350, 100) && game.gameState == Game.STATE.GameOver){
            for (int i = 0; i < 15; i++) {
                handler.addObject(new MenuEffect(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.MenuEffect, handler));
            }
            newHighscore = true;
            appended = false;
            hud.setScore(0);
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

    public void tick() throws IOException {
        BufferStrategy bs = game.getBufferStrategy();
        Graphics g = bs.getDrawGraphics();
        this.render(g);
    }

    public void render(Graphics g) throws IOException {
        if(game.gameState == Game.STATE.Menu) {
            Font font = new Font("comic sans ms", 1, 90);
            Font font2 = new Font("segoe print", 1, 25);
            Font font3 = new Font("comic sans ms", 1, 15);
            Font font4 = new Font("simsun", 1, 30);

            g.setFont(font);
            g.setColor(Color.magenta);
            g.drawString("DODGEWAVE", 200, 210);

            g.setFont(font3);
            g.setColor(Color.pink);
            g.drawString("~ Made by Jill Jessurun ~", 388, 255);

            int highScore = 0;
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null){
                int score = Integer.parseInt(line.trim());   // parse each line as an int
                if (score > highScore){
                    highScore = score;
                }
                line = reader.readLine();
            }
            reader.close();

            g.setFont(font4);
            g.setColor(Color.pink);
            g.drawString("High score: " + highScore, 367, 330);

            //button play
            g.setFont(font2);
            g.setColor(Color.white);
            g.drawRect(310, 430, 350, 100);
            g.setColor(Color.yellow);
            g.drawString("Play", 454, 488);

            //button options
            g.setColor(Color.white);
            g.drawRect(310, 600, 350, 100);
            g.setColor(Color.yellow);
            g.drawString("Options", 430, 655);

            //button quit
            g.setColor(Color.white);
            g.drawRect(310, 770, 350, 100);
            g.setColor(Color.yellow);
            g.drawString("Quit", 451, 826);

        }else if(game.gameState == Game.STATE.Options){
            Font font3 = new Font("comic sans ms", 1, 25);
            Font font2 = new Font("segoe print", 1, 25);
            Font font = new Font("comic sans ms", 1, 60);
            g.setFont(font);
            g.setColor(Color.lightGray);

            g.drawString("Options", 369, 130);
            //g.drawString("Use the keys   W A S D   to dodge the enemies!", 215, 190);
            g.setFont(font3);
            if(difficulty.equals("medium")) {
                g.setColor(Color.orange);
                g.drawString("Current difficulty: " + difficulty, 310, 220);
            }else if(difficulty.equals("hard")){
                g.setColor(Color.red);
                g.drawString("Current difficulty: " + difficulty, 330, 220);
            }else if(difficulty.equals("easy")){
                g.setColor(Color.green);
                g.drawString("Current difficulty: " + difficulty, 330, 220);
            }
            g.setFont(font2);

            //button help
            g.setColor(Color.white);
            g.drawRect(310, 430, 350, 100);
            g.setColor(Color.yellow);
            g.drawString("Help", 454, 488);

            //button difficulty
            g.setColor(Color.white);
            g.drawRect(310, 600, 350, 100);
            g.setColor(Color.yellow);
            g.drawString("Change difficulty", 385, 655);

            //button go back
            g.setColor(Color.white);
            g.drawRect(310, 770, 350, 100);
            g.setColor(Color.yellow);
            g.drawString("Home", 451, 826);

        }else if(game.gameState == Game.STATE.Difficulty){
            Font font2 = new Font("segoe print", 1, 25);
            g.setFont(font2);
            g.setColor(Color.white);

            //button EASY
            g.setColor(Color.white);
            g.drawRect(320, 120, 350, 100);
            g.setColor(Color.yellow);
            g.drawString("Easy", 459, 180);

            //button MEDIUM
            g.setColor(Color.white);
            g.drawRect(320, 300, 350, 100);
            g.setColor(Color.yellow);
            g.drawString("Medium", 447, 358);

            //button HARD
            g.setColor(Color.white);
            g.drawRect(320, 480, 350, 100);
            g.setColor(Color.yellow);
            g.drawString("Hard", 459, 538);

            //button go back
            g.setColor(Color.white);
            g.drawRect(320, 770, 350, 100);
            g.setColor(Color.yellow);
            g.drawString("Back", 460, 825);

        }else if(game.gameState == Game.STATE.Help){
            Font font = new Font("comic sans ms", 1, 25);
            Font font2 = new Font("comic sans ms", 1, 30);
            g.setFont(font2);
            g.setColor(Color.lightGray);

            g.drawString("Gameplay:", 405, 150);
            g.drawString("Move the white square (you) and dodge the enemies.", 95, 190);
            g.drawString("Control keys: ", 388, 270);
            g.drawString(" W = go up ", 400, 310);
            g.drawString(" A = go left ", 400, 350);
            g.drawString(" S = go down ", 400, 390);
            g.drawString(" D = go right ", 400, 430);

            //button go back
            g.setFont(font);
            g.setColor(Color.white);
            g.drawRect(320, 770, 350, 100);
            g.setColor(Color.yellow);
            g.drawString("Back", 460, 825);
        }else if(game.gameState == Game.STATE.GameOver){
            Font font = new Font("segoe print", 1, 25);
            Font font2 = new Font("comic sans ms", Font.ITALIC, 80);
            Font font3 = new Font("comic sans ms", 1, 25);
            g.setFont(font2);
            g.setColor(Color.red);

            g.drawString("GAME OVER", 250, 300);

            g.setFont(font3);
            g.setColor(Color.lightGray);
            g.drawString("Your final score is: " + hud.getScore(), 340, 460);

            //read file and look if the old highest score is lower than the new one
            int highScore = hud.getScore();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null){
                int score = Integer.parseInt(line.trim());   // parse each line as an int
                if (score > highScore){
                        highScore = score;
                        newHighscore = false;
                }

                line = reader.readLine();
            }
            reader.close();

            if (!appended && newHighscore) {
                //append highscore to the file
                BufferedWriter output = new BufferedWriter(new FileWriter(file, true));
                output.newLine();
                output.append("" + highScore);
                output.close();
                appended = true;
            }

            if(hud.getScore() == highScore ) {
                g.drawString("Congratulations! That is a new high score! ", 240, 500);
            }else{
                g.drawString("Unfortunately not a new high score. Try again! ", 210, 500);
            }

            //button home screen
            g.setFont(font);
            g.setColor(Color.white);
            g.drawRect(310, 770, 350, 100);
            g.setColor(Color.yellow);
            g.drawString("Home", 451, 826);

        }

    }
}
