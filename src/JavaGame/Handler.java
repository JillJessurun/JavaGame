package JavaGame;

import java.awt.*;
import java.util.LinkedList;

public class Handler {//render all objects we are going to make

    LinkedList<GameObject> object = new LinkedList<GameObject>();//list of all your objects

    public void tick(){
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);//gets an object from the list above based on index i

            tempObject.tick();
        }
    }
    public void render(Graphics g){
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            tempObject.render(g);
        }
    }

    public void clearEnemies() {
        for (int i = 0; i < this.object.size(); i++) {
            GameObject tempObject = this.object.get(i);
            if (tempObject.getId() != ID.Player) {
                this.removeObject(tempObject);
                i--;
            }
        }
    }

    public void addObject(GameObject object){
        this.object.add(object);
    }

    public void removeObject(GameObject object){
        this.object.remove(object);
    }

    public LinkedList<GameObject> getObject() {
        return object;
    }
}
