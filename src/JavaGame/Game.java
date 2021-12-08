//10
package JavaGame;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 1000;
    private Thread thread;
    private boolean running = false;
    private Handler handler;
    private Random r;
    private HUD hud;
    private Spawner spawner;
    private Menu menu;
    private String difficulty = "easy";

    //pages menu screen
    public enum STATE {
        Menu,
        Options,
        Difficulty,
        Help,
        Game
    }

    public STATE gameState = STATE.Menu;

    public Game(){
        handler = new Handler();
        menu = new Menu(this, handler, difficulty);
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(menu);

        new Window(WIDTH,HEIGHT,"Sill's game", this);

        hud = new HUD(handler);
        spawner = new Spawner(handler, hud, this);
        r = new Random();

        if (gameState == STATE.Game){
            handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler));
            handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, handler));
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
                tick();
                delta--;
            }
            if(running){
                render();
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

    private void tick(){
        handler.tick();
        if (gameState == STATE.Game){
            hud.tick();
            spawner.tick();
        }else if(gameState == STATE.Menu){
            menu.tick();
        }
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        if (gameState == STATE.Game){
            handler.render(g);
            hud.render(g);
        }else if(gameState == STATE.Menu || gameState == STATE.Options || gameState == STATE.Difficulty || gameState == STATE.Help){
            menu.render(g);
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

    public static void main(String[] args) {
        Game game = new Game();
    }

}
