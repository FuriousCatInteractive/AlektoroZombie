
import Entities.GameBaseEntity;
import Entities.Mob;
import Entities.Player;
import org.jsfml.graphics.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by coco on 14-10-10.
 */
public class TextureManagerEntity {

    private int compteurAnimation;

    public TextureManagerEntity(Sprite sprite, boolean mob){
        compteurAnimation=0;
        Texture poule_spriteSheet = loadTexture("rsc/img/zombie.png");

        sprite.setTexture(poule_spriteSheet);

        updateTexture(sprite,3, mob);
        sprite.setScale(4f,4f);
        //System.out.println(sprite.getGlobalBounds());


    }

    public void updateTexture(Sprite sprite, int direction, boolean mob){
        IntRect rect ;

        if(mob){
            compteurAnimation++;
            if(compteurAnimation==60)
                compteurAnimation=0;
            //System.out.println(compteurAnimation);
        }
        else
            compteurAnimation=0;


        switch (direction){
            case 1://bas
                 rect = new IntRect((compteurAnimation/15)*sprite.getTexture().getSize().x/4,
                        0,
                        sprite.getTexture().getSize().x/4,
                        sprite.getTexture().getSize().y/4);
                break;
            case 2:
                rect = new IntRect((compteurAnimation/15)*sprite.getTexture().getSize().x/4,
                         sprite.getTexture().getSize().y/4,
                        sprite.getTexture().getSize().x/4,
                        sprite.getTexture().getSize().y/4);
                break;
            case 3:
                rect = new IntRect((compteurAnimation/15)*sprite.getTexture().getSize().x/4,
                        sprite.getTexture().getSize().y/2,
                        sprite.getTexture().getSize().x/4,
                        sprite.getTexture().getSize().y/4);
                break;
            case 4:
                rect = new IntRect((compteurAnimation/15)*sprite.getTexture().getSize().x/4,
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
            }
            else if(it instanceof Player) {
                it.setTexture(loadTexture("rsc/img/zombi.png"));
            }
        }
    }
}
