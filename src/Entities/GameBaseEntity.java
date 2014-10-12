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
        this.isVisible = true;
        this.direction = 1;
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

    public int getAngle() {

        return angle;
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

    
    public void setPositionEntity(boolean cartesien, int XOrAngle, int YOrRadius)
    {
    	if(cartesien){
    		this.setPosition(XOrAngle, YOrRadius);
    		this.radius = (int) Math.sqrt(Math.pow(XOrAngle, 2) + Math.pow(YOrRadius, 2));
    		if(radius>0){
    			angle = (int) Math.atan(YOrRadius/XOrAngle);
    		}else if(radius<0){
    			angle = (int) (Math.atan(YOrRadius/XOrAngle)+Math.PI);
    		}
    	}
    	else
    	{
    		this.angle = XOrAngle;
    		this.radius = YOrRadius;Player P=Player.getInstance();
            float Px=350;
            float Py=350;
            this.setPosition((int)(Px+radius * Math.cos(Math.toRadians(angle))),
                    (int)(Py+radius * Math.sin(Math.toRadians(angle))));

        }
    }


    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public abstract void touch();

    public void detectCollision() {
        for(int i=0; i<EntityManager.getEntityList().size(); ++i) {
            if(EntityManager.getEntityList().get(i) instanceof GameBaseEntity) {
                if(this.getGlobalBounds().intersection(EntityManager.getEntityList().get(i).getGlobalBounds()) != null) {
                    touch();
                }
            }
        }
    }

}

