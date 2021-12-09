package JavaGame;

import java.awt.*;
import java.util.Random;

public class Boss2Enemy extends GameObject{
    private Handler handler;
    Random r = new Random();
    private int timer = 80;
    private int timer2 = 50;

    public Boss2Enemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = 0;
        velY = 2;
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 64, 64);
    }

    public void tick() {
        x += velX;
        y += velY;

        if (timer <= 0){
            velY = 0;
        }else{
            timer--;
        }

        if(timer<=0){
            timer2--;
        }
        if (timer2 <= 0){
            if(velX==0){
                velX = 5;
            }

            int spawn = r.nextInt(5);
            if(spawn==0){
                handler.addObject(new Boss1EnemyBullet((int)x+48, (int)y+48, ID.BasicEnemy, handler));
            }
        }

        //if (y<= 0 || y >= Game.HEIGHT - 32) {
        //velY *= -1;
        //}
        if (x<= 0 || x >= Game.WIDTH - 70) {
            velX *= -1;
        }

        //handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.red, 64, 64, 0.008, handler));
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)x, (int)y, 64, 64);
    }
}
