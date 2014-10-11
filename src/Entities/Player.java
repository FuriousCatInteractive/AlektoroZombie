package Entities;

import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

/**
 * Created by steven on 10/10/14.
 */
public class Player extends MovableEntity{

    static Player singleton = null;

    private Player() {
        super();
        id = 0;
        this.setPosition(50, 50);
    }

    public final static Player getInstance() {
        if(singleton == null) {
            singleton = new Player();
        }
        return singleton;
    }

    public void updateDirection(Vector2i pos, Vector2i sizeWindow) {
        if(pos.x+pos.y>=sizeWindow.x){
            if(sizeWindow.x-pos.x+pos.y>=sizeWindow.x){
                this.setDirection(1);
            }
            else{
                this.setDirection(3);
            }
        }
        if(pos.x+pos.y<=sizeWindow.x) {
            if (sizeWindow.x - pos.x + pos.y <= sizeWindow.x) {
                this.setDirection(4);
            }
            else{
                this.setDirection(2);
            }
        }
    }
}
