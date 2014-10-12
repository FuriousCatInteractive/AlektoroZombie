package Entities;

import MoveBehavior.MoveBehavior;

/**
 * Created by steven on 10/10/14.
 */
public class Mob extends MovableEntity{

    @Override
    public void touch() {
        this.setVisible(false);
    }

    public Mob(int id, MoveBehavior moveStrategy, int maxSpeed) {
        super(moveStrategy, maxSpeed);
        this.id = id;
        this.setPosition(0,0);
    }
    
    public Mob(int id, MoveBehavior moveStrategy, int maxSpeed, int x, int y) {
        super(moveStrategy, maxSpeed);
        this.id = id;
        this.setPositionEntity(true,x,y);
    }
}