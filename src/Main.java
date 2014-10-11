import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Clock;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Keyboard;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

import java.util.Random;

import static org.jsfml.graphics.Color.*;

public class Main {



    public static void main(String[] args) {

        int WINDOW_H = 600;
        int WINDOW_W = 800;

        RenderWindow window1 = new RenderWindow();
        window1.create(new VideoMode(WINDOW_W ,WINDOW_H), "fenetre JSFML");//-1 = fullscreen
        window1.setFramerateLimit(60);
        window1.setKeyRepeatEnabled(true);
        window1.setMouseCursorVisible(false);

        FloatRect rectWindow1 = new FloatRect(new Vector2f(15,15), new Vector2f(WINDOW_W-30,WINDOW_H-30));
        System.out.println("top: "+rectWindow1.top+" left: "+rectWindow1.left+" width: "+rectWindow1.height);

        //utiliser un getresolution pour adapter à la fenêtre
        //   System.out.println(window1.getSize().y);


     /*   BootSplash bootsplash1 = new BootSplash(window1);
        long debut_bootsplash = System.currentTimeMillis();
        int duree=6;

        while (System.currentTimeMillis()-debut_bootsplash<duree*1000 && window1.isOpen()) {

            if (Keyboard.isKeyPressed(Keyboard.Key.ESCAPE)) {
                window1.close();
            }
            // On gère les événements
            for (Event event : window1.pollEvents()) {
                if (event.type == Event.Type.CLOSED) {
                    // Si l'utilisateur clique sur la croix rouge alors on ferme
                    // la fenêtre
                    window1.close();
                }
            }

            bootsplash1.update();
            window1.draw(bootsplash1);

            window1.display();
            window1.clear(BLACK);
        }
        window1.close();
    }*/


        Sprite test = new Sprite();
        TextureManager textureManager1 = new TextureManager(test);

        while (window1.isOpen()){

            if (Keyboard.isKeyPressed(Keyboard.Key.ESCAPE)) {
                window1.close();
            }
            // On gère les événements
            for (Event event : window1.pollEvents()) {
                if (event.type == Event.Type.CLOSED) {
                    // Si l'utilisateur clique sur la croix rouge alors on ferme
                    // la fenêtre
                    window1.close();
                }
                if (event.type == Event.Type.KEY_PRESSED) {
                    event.asKeyEvent();
                    if (Keyboard.isKeyPressed(Keyboard.Key.LEFT))
                        textureManager1.updateTexture(test, 2);
                    if (Keyboard.isKeyPressed(Keyboard.Key.RIGHT))
                        textureManager1.updateTexture(test,3);
                    if (Keyboard.isKeyPressed(Keyboard.Key.UP))
                        textureManager1.updateTexture(test,1);
                    if (Keyboard.isKeyPressed(Keyboard.Key.DOWN))
                        textureManager1.updateTexture(test,4);
                }
            }
            window1.draw(test);

            window1.display();
            window1.clear(BLACK);
        }


        }


    }