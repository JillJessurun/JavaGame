package JavaGame;

import java.awt.*;
import java.util.Random;

public class Spawner {

    private Handler handler;
    private HUD hud;
    private Random r = new Random();
    private int scoreKeep = 0;
    private Game game;

    public Spawner(Handler handler, HUD hud, Game game) {
        this.handler = handler;
        this.hud = hud;
        this.game = game;
    }

    public void spawn(ID id, int amountOfSpawns){
        if(id.equals(ID.BasicEnemy)) {
            for (int i = 0; i < amountOfSpawns; i++) {
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
            }
        }else if(id.equals(ID.FastEnemy)) {
            for (int i = 0; i < amountOfSpawns; i++) {
                handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, handler));
            }
        }else if(id.equals(ID.UltraEnemy)) {
            for (int i = 0; i < amountOfSpawns; i++) {
                handler.addObject(new UltraEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.UltraEnemy, handler));
            }
        }else if(id.equals(ID.SmartEnemy)) {
            for (int i = 0; i < amountOfSpawns; i++) {
                handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.SmartEnemy, handler));
            }
        }else if(id.equals(ID.SlowEnemy)) {
            for (int i = 0; i < amountOfSpawns; i++) {
                handler.addObject(new SlowEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.SlowEnemy, handler));
            }
        }
    }

    public void tick() {
        scoreKeep++;

        if (scoreKeep >= 100) {
            scoreKeep = 0;
            hud.setLevel((int) (hud.getLevel() + 1));

            if (!handler.object.isEmpty()) {
                if (hud.getLevel() < 5) {
                    spawn(ID.BasicEnemy, 2);
                } else if (hud.getLevel() < 10) {
                    spawn(ID.FastEnemy, 2);
                } else if (hud.getLevel() == 15) {
                    spawn(ID.UltraEnemy, 2);
                } else if (hud.getLevel() == 20) {
                    spawn(ID.SmartEnemy, 1);
                    spawn(ID.UltraEnemy, 2);
                }else if (hud.getLevel() == 25) {
                    handler.clearEnemies();
                    handler.addObject(new Boss1Enemy((Game.WIDTH / 2) - 48, -64, ID.Boss1Enemy, handler));
                }else if (hud.getLevel() == 35){
                    handler.clearEnemies();
                    spawn(ID.FastEnemy, 3);
                }else if (hud.getLevel() >= 36 && hud.getLevel() < 40){
                    spawn(ID.FastEnemy, 3);
                }else if (hud.getLevel() > 40){
                    spawn(ID.SlowEnemy, 3);
                }//level 60 -> new boss :)

                } else {
                    hud.setLevel(1);
                    hud.setHEALTH(100);

                    game.ingameAudio.stopMusic();
                    game.gameState = Game.STATE.GameOver;
                }
            }
        }
    }
