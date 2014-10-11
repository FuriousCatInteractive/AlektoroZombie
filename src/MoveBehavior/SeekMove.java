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
        return Vector2f.mul(MathUtils.normalize(Vector2f.sub(target.getPosition(), entity.getPosition())), entity.getMaxSpeed());
    }
}
