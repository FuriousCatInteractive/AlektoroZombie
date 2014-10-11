package Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by steven on 10/11/14.
 */
public class EntityManager {
    static List<GameBaseEntity> entityList = new ArrayList<GameBaseEntity>();
    static EntityManager instance = null;

    private EntityManager() {

    }

    public final static EntityManager getIntance() {
        if(instance == null) {
            instance = new EntityManager();
        }
        return instance;
    }

    public void addEntity(GameBaseEntity entity) {
        entityList.add(entity);
    }

    public GameBaseEntity getEntity(String entity, int id) throws Exception{
        for(GameBaseEntity it : entityList) {
            String[] str = it.getClass().getName().split("\\.");
            if(str[str.length-1].equals(entity) && it.getId() == id) {
                return it;
            }
        }
        throw new Exception(this.getClass().getName() + ": There a not this entity in the list");
    }
}
