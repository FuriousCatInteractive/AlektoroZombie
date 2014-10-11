package Parsing;

import java.io.Serializable;
import java.util.Objects;
 
 
public class Enemy implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 5L;
    int angle;
    long time;
     
    public Enemy()
    {
         
    }
     
    public Enemy(int angle, long time)
    {
        this.angle=angle;
        this.time=time;
    }
     
    public int getAngle() {
        return angle;
    }
    public void setAngle(int angle) {
        this.angle = angle;
    }
    public long getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
    }
     
    public String toString()
    {
    if(angle>45 && angle<135)return "-->";
    if(angle>135 && angle<225)return "BAS";
    if(angle>225 && angle<315)return "<--";
    if (angle>=0 && angle<45 ||angle>315 && angle<360) return "TOP";
    return Objects.toString(angle, null);
    }
}