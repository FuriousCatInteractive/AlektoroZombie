
import org.jsfml.graphics.*;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by coco on 14-10-10.
 */
public class TextureManager {

    private int compteurAnimation;

    public TextureManager(Sprite sprite){
        compteurAnimation=0;
        Texture poule_spriteSheet = loadTexture("rsc/img/zombie.png");

        sprite.setTexture(poule_spriteSheet);

        updateTexture(sprite,3);
        sprite.setScale(4f,4f);
        System.out.println(sprite.getGlobalBounds());


    }

    public void updateTexture(Sprite sprite, int direction){
        IntRect rect ;

        compteurAnimation++;
        if(compteurAnimation==4)
            compteurAnimation=0;
        System.out.println(compteurAnimation);

        switch (direction){
            case 1://bas
                 rect = new IntRect((compteurAnimation)*sprite.getTexture().getSize().x/4,
                        0,
                        sprite.getTexture().getSize().x/4,
                        sprite.getTexture().getSize().y/4);
                break;
            case 2:
                rect = new IntRect((compteurAnimation)*sprite.getTexture().getSize().x/4,
                         sprite.getTexture().getSize().y/4,
                        sprite.getTexture().getSize().x/4,
                        sprite.getTexture().getSize().y/4);
                break;
            case 3:
                rect = new IntRect((compteurAnimation)*sprite.getTexture().getSize().x/4,
                        sprite.getTexture().getSize().y/2,
                        sprite.getTexture().getSize().x/4,
                        sprite.getTexture().getSize().y/4);
                break;
            case 4:
                rect = new IntRect((compteurAnimation)*sprite.getTexture().getSize().x/4,
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
        System.out.println(rect);
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

}
