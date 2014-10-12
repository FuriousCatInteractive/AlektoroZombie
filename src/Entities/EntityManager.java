package Entities;

import java.util.LinkedList;
import java.util.List;

/**
 * Package ${PACKAGE_NAME}
 * Created by steven on 10/11/14.
 */
public class EntityManager {
    static private List<GameBaseEntity> entityList = new LinkedList<GameBaseEntity>();
    static private EntityManager instance = null;

    private EntityManager() {

    }

    public static EntityManager getIntance() {
        if(instance == null) {
            instance = new EntityManager();
        }
        return instance;
    }

    public static void addEntity(GameBaseEntity entity) {
        entityList.add(entity);
    }

    public static GameBaseEntity getEntity(String entity, int id) throws Exception{
        for(GameBaseEntity it : entityList) {
            String[] str = it.getClass().getName().split("\\.");
            if(str[str.length-1].equals(entity) && it.getId() == id) {
                return it;
            }
        }
        throw new Exception("This entity doesn't exist");
    }

    public static List<GameBaseEntity> getEntityList() {
        return entityList;
    }
}
