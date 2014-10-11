package Entities;

import MoveBehavior.MoveBehavior;

/**
 * Created by steven on 10/10/14.
 */
public abstract class MovableEntity extends GameBaseEntity {
    private MoveBehavior moveStrategy;
    private int maxSpeed;

    public MovableEntity() {
        super();
        moveStrategy = null;
        maxSpeed = 0;
    }

    public MovableEntity(MoveBehavior moveStrategy, int maxSpeed) {
        super();
        this.moveStrategy = moveStrategy;
        this.maxSpeed = maxSpeed;
    }

    public void move() throws Exception{
        if(moveStrategy == null) {
            throw new Exception(this.getClass().getName() + ": Have not move behavior");
        }
        else {
            moveStrategy.move(this);
        }
    }

    public MoveBehavior getMoveStrategy() {
        return moveStrategy;
    }

    public void setMoveStrategy(MoveBehavior moveStrategy) {
        this.moveStrategy = moveStrategy;
    }
}
