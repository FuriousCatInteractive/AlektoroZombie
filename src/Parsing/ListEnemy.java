package Parsing;

import java.io.Serializable;
import java.util.ArrayList;
 
 
public class ListEnemy implements Serializable  {
    static private final long serialVersionUID=6L;
     
    public ArrayList<Enemy> LEnemy;
    
    public int lenght()
    {
        return LEnemy.size();
    }
    public ListEnemy()
    {
        LEnemy=new ArrayList<Enemy>();
    }
     
    public void addEnemy(int angle,long timing)
    {
        Enemy e=new Enemy(angle,timing);
        LEnemy.add(e);
    }
    public boolean afficher(long StartT,int compt) {
           
            
           if(LEnemy.get(compt).getTime()<=System.currentTimeMillis()-StartT && compt<=LEnemy.size())
           {
               System.out.println(LEnemy.get(compt).toString());
               return true;
                
           }
           return false;
    }
}