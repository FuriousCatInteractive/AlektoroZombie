package PlaceEntities;

import java.io.*;
import java.util.List;


import Entities.GameBaseEntity;
import Entities.Mob;
import Entities.Player;
import MoveBehavior.SeekMove;
import Parsing.ListEnemy;

public class PlaceMobs {
	
	final private int radiusSpawnMobs = 40;
	private ListEnemy LE;
	private Mob[] listMobs;
	private int cptInit;
	
	

	public PlaceMobs(Player player){
		LE = DeserializationListEnemy();
		for(int i = 0; i < LE.lenght();i++)
		{
			listMobs[i] = new Mob(i, new SeekMove(player), 1);
		}
		cptInit = 0;
	}
	
	public void placement(int angleMob, GameBaseEntity Mob)
	{
		listMobs[cptInit].setPositionEntity(false, angleMob, radiusSpawnMobs);
		cptInit++;
	}
	
	public ListEnemy DeserializationListEnemy()
    {
        try {
            FileInputStream fis = new FileInputStream("Zelda2.serial");
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
}
