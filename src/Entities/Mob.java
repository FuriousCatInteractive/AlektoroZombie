package Entities;

import Graphics.TextureManager;
import MoveBehavior.MoveBehavior;
import org.jsfml.system.Vector2i;

/**
 * Created by steven on 10/10/14.
 */
public class Mob extends MovableEntity{

	private long spawnTime;
    private int dieAnim;

    private boolean movable;

    public void decDieAnim() {
        --dieAnim;
        if(dieAnim <=0) {
            this.setVisible(false);
        }
    }

    @Override
    public void touch() {
        this.moveStrategy = null;
        this.setAlive(false);
        TextureManager.loadImageOnSprite(this, "rsc/img/blood.png", 2f, 2f);
    }

    public Mob(int id, MoveBehavior moveStrategy, int maxSpeed) {
        super(moveStrategy, maxSpeed);
        this.id = id;
        this.setPosition(0,0);
        this.dieAnim = 30;
        movable=false;
    }
 
    public Mob(int id, MoveBehavior moveStrategy, int maxSpeed, boolean cartesien, int xOrAngle, int yOrRadius, long time) {
        super(moveStrategy, maxSpeed);
        this.id = id;
        if(cartesien)
        {
        	this.setPositionEntity(true,xOrAngle,yOrRadius);
        }else if (!cartesien){
        	this.setPositionEntity(false,xOrAngle,yOrRadius);
        }
        spawnTime = time;
        movable=false;
        this.dieAnim = 30;
    }

    public boolean getMovable() {
        return movable;
    }

    public void setMovable(boolean movable) {
        this.movable = movable;
    }


    public long getSpawnTime()
    {
    	return spawnTime;
    }

    public void updateDirection(Vector2i pos, Vector2i sizeWindow) {
        if(pos.x+pos.y>=sizeWindow.x){
            if(sizeWindow.x-pos.x+pos.y>=sizeWindow.x){
                this.setDirection(4);
            }
            else{
                this.setDirection(2);
            }
        }
        if(pos.x+pos.y<=sizeWindow.x) {
            if (sizeWindow.x - pos.x + pos.y <= sizeWindow.x) {
                this.setDirection(1);
            }
            else{
                this.setDirection(3);
            }
        }
    }

}