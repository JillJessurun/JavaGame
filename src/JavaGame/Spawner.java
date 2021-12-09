package JavaGame;

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

    public void tick() {
        scoreKeep++;

        if (scoreKeep >= 100) {
            scoreKeep = 0;
            hud.setLevel((int) (hud.getLevel() + 1));

            if (!handler.object.isEmpty()) {
                if (hud.getLevel() < 5) {
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
                } else if (hud.getLevel() < 10) {
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, handler));
                } else if (hud.getLevel() == 15) {
                    handler.addObject(new UltraEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.UltraEnemy, handler));
                } else if (hud.getLevel() == 20) {
                    handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.SmartEnemy, handler));
                    handler.addObject(new UltraEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.UltraEnemy, handler));
                }else if (hud.getLevel() == 25) {
                    handler.clearEnemies();
                    handler.addObject(new Boss1Enemy((Game.WIDTH / 2) - 48, -64, ID.Boss1Enemy, handler));
                }else if (hud.getLevel() >= 35 && hud.getLevel() < 40){
                    handler.clearEnemies();
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, handler));
                }else if (hud.getLevel() >= 40 && hud.getLevel() < 45){
                    handler.addObject(new UltraEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.UltraEnemy, handler));
                }else if (hud.getLevel() == 45){
                    handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.SmartEnemy, handler));
                }

                } else {
                    hud.setLevel(1);
                    hud.setHEALTH(100);

                    game.gameState = Game.STATE.GameOver;
                }
            }
        }
    }
