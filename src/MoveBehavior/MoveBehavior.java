package MoveBehavior;

import Entities.MovableEntity;
import org.jsfml.system.Vector2f;

/**
 * Created by steven on 10/10/14.
 */
public interface MoveBehavior {
    public Vector2f move(MovableEntity entity);
}
