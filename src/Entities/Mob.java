package Entities;

import MoveBehavior.MoveBehavior;

/**
 * Created by steven on 10/10/14.
 */
public class Mob extends MovableEntity{

	private long spawnTime;

    @Override
    public void touch() {
        this.setVisible(false);
    }

    public Mob(int id, MoveBehavior moveStrategy, int maxSpeed) {
        super(moveStrategy, maxSpeed);
        this.id = id;
        this.setPosition(0,0);
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