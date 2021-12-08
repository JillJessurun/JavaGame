package JavaGame;

import java.awt.*;
import java.util.Random;

public class MenuEffect extends GameObject{
    private Handler handler;
    Random r = new Random();

    private Color col;

    public MenuEffect(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = r.nextInt(8, 12);
        velY = r.nextInt(8, 12);

        col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
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

        handler.addObject(new Trail((int)x, (int)y, ID.Trail, col, 16, 16, 0.03, handler));
    }

    public void render(Graphics g) {
        g.setColor(col);
        g.fillRect((int)x, (int)y, 16, 16);
    }
}
