package World;

import org.jsfml.system.Vector2f;

import Entities.Player;
import MoveBehavior.MathUtils;

public class Score {

    static private int score=0;

    static private int lostPoints=0;
    static private int chickenKill = 0;

    static public void inc(int res)
    {
        score+=res;
    }
    static public void dec(int dec)
    {
        lostPoints+=dec;
        score-=2;
    }
    static public int getScore()
    {
        return score;
    }

    public static int getLostPoints() {
        return lostPoints;
    }

    public static int getChickenKill() {
        return chickenKill;
    }

    public static void setChickenKill(int chickenKill) {
        Score.chickenKill = chickenKill;
    }

    public static void reset() {
        score=0;
        lostPoints=0;
        chickenKill = 0;
    }

    public static void incKillChicken() {
        chickenKill++;
    }
}
