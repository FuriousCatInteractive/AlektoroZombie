package Entities;

/**
 * Created by steven on 10/10/14.
 */
public class Player extends GameBaseEntity{

    private static Player singleton;

    private Player() {
        super();
    }

    public Player getInstance() {
        singleton = new Player();
        return singleton;
    }
}
