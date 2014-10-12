package MoveBehavior;

import Entities.Bullet;
import Entities.MovableEntity;
import Entities.Player;
import org.jsfml.system.Vector2f;

/**
 * Created by steven on 10/11/14.
 */
public class BulletMove implements MoveBehavior {
    @Override
    public Vector2f move(MovableEntity entity) {
        return Vector2f.mul(((Bullet)entity).getPlayerViewFinder(), entity.getMaxSpeed());
    }
}
