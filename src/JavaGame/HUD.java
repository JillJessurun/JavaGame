package JavaGame;

import java.awt.*;

public class HUD {
    public static int HEALTH = 100;
    private int greenValue = 255;
    private boolean yellow = false;
    private boolean red = false;
    private int score = 0;
    private int level = 1;
    private Handler handler;

    public HUD (Handler handler) {
        this.handler = handler;
    }

    public void tick(){
        HEALTH = (int) Game.clamp(HEALTH,0,100);

        if (HEALTH <= 50 && HEALTH > 30){
            yellow = true;
        }else if(HEALTH <= 30 && HEALTH > 0){
            yellow = false;
            red = true;
        }else if(HEALTH == 0){
            if (!handler.object.isEmpty()) {
                red = false;
                yellow = false;
                handler.object.remove();
            }
        }
        if (HEALTH > 0){
            score++;
        }

    }

    public void render(Graphics g){
        g.setColor(Color.gray);
        Font font2 = new Font("arial", 1, 11);
        g.setFont(font2);
        g.fillRect(15, 15, 200, 40);
        if(yellow){
            g.setColor(Color.yellow);
        }else if(red){
            g.setColor(Color.red);
        }else{
            g.setColor(Color.green);
        }
        g.fillRect(15, 15, HEALTH * 2, 40);
        g.setColor(Color.white);
        g.drawRect(15, 15, 200, 40);
        g.drawString(HEALTH+"%", 225, 40);
        g.drawString("Score: " + score, 16, 78);
        g.drawString("Level: " + level, 17, 94);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public float getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setHEALTH(int HEALTH) {
        HUD.HEALTH = HEALTH;
    }
}
