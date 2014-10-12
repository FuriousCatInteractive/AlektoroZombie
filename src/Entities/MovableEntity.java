package Entities;

import MoveBehavior.MoveBehavior;
import org.jsfml.system.Vector2f;

/**
 * Created by steven on 10/10/14.
 */
public abstract class MovableEntity extends GameBaseEntity {
    protected MoveBehavior moveStrategy;
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

    public void moveEntity() throws Exception{
        if (this instanceof Mob && !((Mob)this).getMovable())return;
        if(moveStrategy != null) {
            Vector2f velocity = moveStrategy.move(this);
            this.setPosition(Vector2f.add(this.getPosition(), velocity));
        }
        else {
            throw new Exception(this.getClass().getName() + ": Have not move behavior");
        }
    }

    public MoveBehavior getMoveStrategy() {
        return moveStrategy;
    }

    public void setMoveStrategy(MoveBehavior moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}
