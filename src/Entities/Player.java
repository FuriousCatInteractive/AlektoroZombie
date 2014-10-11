package Entities;

import Graphics.TextureManager;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

/**
 * Created by steven on 10/10/14.
 */
public class Player extends MovableEntity{
    private Vector2f vectorViewFinder;
    private static int bulletId = 0;

    static Player singleton = null;

    private Player() {
        super();
        id = 0;
        this.setPosition(50, 50);
        this.vectorViewFinder = new Vector2f(0,0);
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

    public void fire() {
        EntityManager.addEntity(new Bullet(this, bulletId));
        try {
            TextureManager.mergeTextureSprite(EntityManager.getEntity("Bullet", bulletId));
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        ++bulletId;
    }

    public Vector2f getVectorViewFinder() {
        return vectorViewFinder;
    }

    public void setVectorViewFinder(Vector2f vectorViewFinder) {
        this.vectorViewFinder = vectorViewFinder;
    }
}
