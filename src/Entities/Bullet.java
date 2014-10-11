package Entities;

import MoveBehavior.BulletMove;
import MoveBehavior.MathUtils;
import org.jsfml.system.Vector2f;

/**
 * Created by steven on 10/11/14.
 */
public class Bullet extends MovableEntity {
    private Vector2f playerViewFinder;

    public Bullet(Player player, int id) {
        super(new BulletMove(), 5);
        this.playerViewFinder = MathUtils.normalize(player.getVectorViewFinder());
        this.id = id;
        this.setPosition(player.getPosition());
        this.setDirection(1);
        this.setVisible(true);
    }

    public Vector2f getPlayerViewFinder() {
        return playerViewFinder;
    }

    public void setPlayerViewFinder(Vector2f vector) {
        this.playerViewFinder = vector;
    }
}
