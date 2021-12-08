package JavaGame;

import java.awt.*;

public class UltraEnemy extends GameObject{
    private Handler handler;

    public UltraEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = 9;
        velY = 6;
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 16, 16);
    }

    public void tick() {
        x += velX;
        y += velY;

        if (y<= 0 || y >= Game.HEIGHT - 32) {
            velY *= -1;
        }
        if (x<= 0 || x >= Game.WIDTH - 32) {
            velX *= -1;
        }

        handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.magenta, 16, 16, 0.06, handler));
    }

    public void render(Graphics g) {
        g.setColor(Color.magenta);
        g.fillRect((int)x, (int)y, 16, 16);
    }
}
