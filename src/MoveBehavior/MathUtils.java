package MoveBehavior;

import org.jsfml.system.Vector2f;

/**
 * Created by steven on 10/11/14.
 */
public class MathUtils {
    public final static Vector2f normalize(Vector2f vector) {
        return new Vector2f(vector.x/norm(vector), vector.y/norm(vector));
    }

    public final static float norm(Vector2f vector) {
        return (float)Math.sqrt(Math.pow(vector.x, 2)+Math.pow(vector.y, 2));
    }
}
