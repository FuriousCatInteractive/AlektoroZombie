package MoveBehavior;

import Entities.GameBaseEntity;
import Entities.MovableEntity;
import org.jsfml.system.Vector2f;

import java.util.Map;

/**
 * Created by steven on 10/10/14.
 */
public class SeekMove implements MoveBehavior{
    private MovableEntity target;

    public SeekMove(MovableEntity target) {
        this.target = target;
    }
    @Override
    public Vector2f move(MovableEntity entity) {
        return Vector2f.mul(normalize(Vector2f.sub(target.getPosition(), entity.getPosition())), entity.getMaxSpeed());
    }

    private Vector2f normalize(Vector2f vector) {
        float norm = (float)Math.sqrt(Math.pow(vector.x, 2)+Math.pow(vector.y, 2));
        return new Vector2f(vector.x/norm, vector.y/norm);
    }
}
