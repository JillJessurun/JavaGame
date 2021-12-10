package JavaGame;

import JavaGame.*;

import java.awt.*;

public class SlowEnemy extends GameObject {
    private Handler handler;

    public SlowEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = 2;
        velY = 1;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    public void tick() {

        x += velX;
        y += velY;

        if (y <= 0 || y >= Game.HEIGHT - 235) {
            velY *= -1;
        }
        if (x <= 0 || x >= Game.WIDTH - 400) {
            velX *= -1;
        }

        handler.addObject(new Trail((int) x, (int) y, ID.Trail, Color.green, 16, 16, 0.06, handler));
    }

    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect((int) x, (int) y, 16, 16);
    }
}
