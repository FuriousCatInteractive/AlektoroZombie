package Entities;

import org.jsfml.graphics.Sprite;

/**
 * Created by steven on 10/10/14.
 */
public abstract class GameBaseEntity extends Sprite{

    // Angle of the entity relative to origin
    private int angle;
    // Radius of the entity relative to origin
    private int radius;

    public GameBaseEntity() {
        this.setPosition(0,0);
        this.angle = 0;
        this.radius = 0;
    }
}
