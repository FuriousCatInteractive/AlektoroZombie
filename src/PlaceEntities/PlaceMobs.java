package PlaceEntities;

import java.io.*;
import java.util.ArrayList;


import Entities.Mob;
import Entities.Player;
import MoveBehavior.SeekMove;
import Parsing.ListEnemy;
import Screens.SelectMusic;

public class PlaceMobs {

    final private int radiusSpawnMobs = (int) Math.sqrt(Math.pow(400,2)+Math.pow(400,2));


    private ArrayList <Mob>listMobs=new ArrayList <Mob>();
    private int cptInit=0;
    private int cptMob=0;


    public Mob nextEnemy(boolean pourVrai,long time)
    {
        if(cptMob>=listMobs.size()){System.out.println("NextEnemy-> Plus d'enemy");return null;}
        if(listMobs.get(cptMob).getSpawnTime()<=time)
        {
            Mob m = listMobs.get(cptMob);
            m.setMovable(true);
            if(pourVrai)cptMob++; //Incremente que dans le cas ou on le fait vraiment, pas en test
            return m;
        }
        return null;
    }

    public PlaceMobs(String songTitle){
        ListEnemy LE = DeserializationListEnemy(songTitle);
        for(int i = 0; i < LE.lenght();i++)
        {
            Mob m = new Mob(i, new SeekMove(Player.getInstance()), SelectMusic.getSpeedPlayerChoice(), false, LE.LEnemy.get(i).getAngle(),radiusSpawnMobs, LE.LEnemy.get(i).getTime());
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
        ListEnemy LE=null;
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
            System.out.println( "a ete deserialise");
            return LE;
        }
        return null;
    }

    public   ArrayList <Mob> getMobsList(){
        return listMobs;
    }
}