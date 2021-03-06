/*
author: Jill Jessurun
date: 2021
goal: dodgewave game in which you have to dodge enemies that are trying to kill you :)

improvement ideas:
- when an enemy spawns, let it sit there for like a second so they cant spawn in you and hurt you
- fix the smart enemy
- more bosses
- different worlds page
- fix that you can go out of screen :/
- the scaling is good on every device
- create a shop with different sprites and upgrades (speed ups, more health, health refills, score multipliers, etc.)
- create a real program when finished (.exe for example), tutorial: https://www.youtube.com/watch?v=jyl9Anruc34
 */
package JavaGame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Game extends Canvas implements Runnable{

    public static final int WIDTH = 1920;
    public static final int HEIGHT = 1080;

    private Thread thread;
    private boolean running = false;
    private Handler handler;
    private Random r;
    private HUD hud;
    private Spawner spawner;
    private Menu menu;
    static Audio mainAudio;
    Audio ingameAudio;
    //public static BufferedImage spriteSheet;

    private String difficulty = "easy";
    public static boolean paused = false;

    File file = new File("C:\\Users\\pc\\IdeaProjects\\JavaGameJillJessurun\\src\\JavaGame\\Highscore.txt");
    BufferedReader reader = new BufferedReader(new FileReader(file));

    //pages menu screen
    public enum STATE {
        Menu,
        Options,
        Difficulty,
        GameOver,
        Help,
        Game
    }

    public STATE gameState = STATE.Menu;

    private BufferedImage spriteSheet;

    public Game() throws IOException {

        handler = new Handler();
        this.mainAudio = new Audio();

        new Window(WIDTH,HEIGHT,"Sill's game", this);
        menu = new Menu(this, handler, difficulty, hud, file, reader);
        hud = new HUD(handler);

        //BufferedImageLoader loader = new BufferedImageLoader();
        //spriteSheet = loader.loadImage("C:\\Users\\pc\\IdeaProjects\\JavaGameJillJessurun\\src\\Images\\steve.jpg");

        this.addKeyListener(new KeyInput(handler, this));

        this.addMouseListener(menu);

        spawner = new Spawner(handler, hud, this);
        r = new Random();

        for (int i = 0; i < 50; i++) {
            handler.addObject(new MenuEffect(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuEffect, handler));
        }


    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void run(){
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                try {
                    tick();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                delta--;
            }
            if(running){
                try {
                    render();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            frames++;
            //if (frames>100){
                //frames = 100;
            //}

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick() throws IOException {
        if (!paused){
            handler.tick();
            if (gameState == STATE.Game){
                hud.tick();
                spawner.tick();
            }else if(gameState == STATE.Menu){
                menu.tick();
            }
        }
    }

    private void render() throws IOException {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();



        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        if (paused){
            g.setColor(Color.cyan);
            Font font = new Font("comic sans ms", 1, 120);
            Font font2 = new Font("arial", 1, 11);
            g.setFont(font);
            g.drawString("PAUSED", 710, 420);
            g.setFont(font2);
            g.setColor(Color.white);
        }

        if (gameState == STATE.Game){
            handler.render(g);
            hud.render(g);
        }else if(gameState == STATE.Menu || gameState == STATE.Options || gameState == STATE.Difficulty || gameState == STATE.Help || gameState == STATE.GameOver){
            menu.render(g);
            handler.render(g);
        }

        g.dispose();
        bs.show();
    }

    public static float clamp(float var, float min, float max){
        if(var >= max){
            return var = max;
        }else if(var <= min){
            return var = min;
        }else{
            return var;
        }
    }

    public static void main(String[] args) throws IOException {
        Game game = new Game();

        mainAudio.playMusic("C:\\Users\\pc\\IdeaProjects\\JavaGameJillJessurun\\src\\Audio\\synthwave.wav");
    }

}
