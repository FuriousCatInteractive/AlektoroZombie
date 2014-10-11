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
    // Allow to know if the entity is alive or not
    private boolean isAlive;
    // Allow to know if the entity is displayable or not
    private boolean isVisible;
    protected int id;
    private int direction;

    /**
     * Default constructor of the GameBaseEntity
     */
    public GameBaseEntity() {
        super();
        this.setPosition(0,0);
        this.angle = 0;
        this.radius = 0;
        this.isAlive = true;
        this.isVisible = false;
    }

    //  Accessors   //
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getAngle() {

        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
