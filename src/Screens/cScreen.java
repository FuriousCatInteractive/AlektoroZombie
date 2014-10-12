package Screens;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by coco on 14-10-11.
 */
public class cScreen {


    public int Run(RenderWindow App){ return  0;}

    protected Sprite loadViseur(RenderWindow App){
        App.setMouseCursorVisible(false);
        Sprite viseur = new Sprite();
        Texture texViseur = new Texture();
        try {
            texViseur.loadFromFile(Paths.get("rsc/img/viseur.png")); // on charge la texture qui se trouve dans notre dossier assets
        } catch (IOException e) {
            e.printStackTrace();
        }
        viseur.setTexture(texViseur);
        viseur.setOrigin(viseur.getLocalBounds().width/2,viseur.getGlobalBounds().height/2);
        return viseur;
    }

}
