package JavaGame;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter {

    public void mousePressed(MouseEvent e){

    }

    public void mouseRelease(MouseEvent e){

    }

    public void tick(){

    }

    public void render(Graphics g){
        g.setColor(Color.white);
        g.drawRect(400, 100, 100, 64);
    }
}
