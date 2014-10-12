package Entities;

import Graphics.TextureManager;
import MoveBehavior.MoveBehavior;

/**
 * Created by steven on 10/10/14.
 */
public class Mob extends MovableEntity{

	private long spawnTime;
    private int dieAnim;

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
    }


    public long getSpawnTime()
    {
    	return spawnTime;
    }
}