package PlaceEntities;

import java.io.*;
import java.util.ArrayList;
import java.util.List;



import Entities.GameBaseEntity;
import Entities.Mob;
import Entities.Player;
import MoveBehavior.SeekMove;
import Parsing.ListEnemy;

public class PlaceMobs {

    final private int radiusSpawnMobs = 40;
    private ListEnemy LE=null;
    private ArrayList <Mob>listMobs=new ArrayList <Mob>();
    private int cptInit;



    public PlaceMobs(Player player, String songTitle){
        LE = DeserializationListEnemy(songTitle);
        for(int i = 0; i < LE.lenght();i++)
        {
            Mob m = new Mob(i, new SeekMove(player), 1);
            listMobs.add(m);
        }
        cptInit = 0;
    }

    public void placement(int angleMob)
    {
        listMobs.get(cptInit).setPositionEntity(false, angleMob, radiusSpawnMobs);
        cptInit++;
    }

    public ListEnemy DeserializationListEnemy(String songTitle)
    {
        try {
            FileInputStream fis = new FileInputStream(songTitle);
            ObjectInputStream ois= new ObjectInputStream(fis);
            try
            {
                LE = (ListEnemy) ois.readObject();
            } finally
            {
                try
                {
                    ois.close();
                } finally
                {
                    fis.close();
                }
            }
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
        if(LE != null) {
            System.out.println( " a ete deserialise");
            return LE;
        }
        return null;
    }

    public   ArrayList <Mob> getMobsList(){
        return listMobs;
    }
}