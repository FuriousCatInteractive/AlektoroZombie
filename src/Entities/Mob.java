package Entities;

import MoveBehavior.MoveBehavior;

/**
 * Created by steven on 10/10/14.
 */
public class Mob extends MovableEntity{
    public Mob(int id, MoveBehavior moveStrategy, int maxSpeed) {
        super(moveStrategy, maxSpeed);
        this.id = id;
    }
}
