
import Entities.GameBaseEntity;
import Entities.Mob;
import Entities.MovableEntity;
import Entities.Player;
import org.jsfml.graphics.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by coco on 14-10-10.
 */
public class TextureManager {

    private int[] compteurAnimation;

    public TextureManager(List<GameBaseEntity> entityList){
        compteurAnimation = new int[entityList.size()];
        for(int i=0;i<entityList.size(); i++){
            compteurAnimation[i]=0;
        }

        mergeTextureSprite(entityList);
    }

    public void updateTexture(Sprite sprite,int numero, int direction){
        IntRect rect ;

        if(sprite instanceof Mob) {
            compteurAnimation[numero]++;
            if (compteurAnimation[numero] == 60)
                compteurAnimation[numero] = 0;
            //System.out.println(compteurAnimation);
        }

        switch (direction){
            case 1://bas
                rect = new IntRect((compteurAnimation[numero]/15)*sprite.getTexture().getSize().x/4,
                        0,
                        sprite.getTexture().getSize().x/4,
                        sprite.getTexture().getSize().y/4);
                break;
            case 2:
                rect = new IntRect((compteurAnimation[numero]/15)*sprite.getTexture().getSize().x/4,
                        sprite.getTexture().getSize().y/4,
                        sprite.getTexture().getSize().x/4,
                        sprite.getTexture().getSize().y/4);
                break;
            case 3:
                rect = new IntRect((compteurAnimation[numero]/15)*sprite.getTexture().getSize().x/4,
                        sprite.getTexture().getSize().y/2,
                        sprite.getTexture().getSize().x/4,
                        sprite.getTexture().getSize().y/4);
                break;
            case 4:
                rect = new IntRect((compteurAnimation[numero]/15)*sprite.getTexture().getSize().x/4,
                        3*(sprite.getTexture().getSize().y/4),
                        sprite.getTexture().getSize().x/4,
                        sprite.getTexture().getSize().y/4);
                break;
            default:
                rect = new IntRect(0,
                        0,
                        sprite.getTexture().getSize().x/4,
                        sprite.getTexture().getSize().y/4);
                break;
        }
        //System.out.println(rect);
        sprite.setTextureRect(rect);
    }


    public Texture loadTexture(String imagePath) {

        Texture textureSprite = new Texture();

        try {
            textureSprite.loadFromFile(Paths.get(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return textureSprite;
    }

    public void mergeTextureSprite(List<GameBaseEntity> entityList) {
        for(GameBaseEntity it: entityList) {
            if(it instanceof Mob) {
                it.setTexture(loadTexture("rsc/img/poule.png"));
                it.setScale(2f,2f);
            }
            else if(it instanceof Player) {
                it.setTexture(loadTexture("rsc/img/zombie.png"));
                it.setScale(4f,4f);
            }
        }
    }

    public void updateList(List<GameBaseEntity> entityList){
        for(GameBaseEntity it: entityList){
            updateTexture(it, it.getId(),it.getDirection());
        }
    }
}
