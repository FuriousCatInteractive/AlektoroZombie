import Entities.EntityManager;
import Entities.Mob;
import Entities.MovableEntity;
import Entities.Player;
import MoveBehavior.SeekMove;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Clock;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Mouse;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

import java.util.Random;

import static org.jsfml.graphics.Color.*;

public class Main {



    public static void main(String[] args) {

        int WINDOW_H = 700;
        int WINDOW_W = 700;

        RenderWindow window1 = new RenderWindow();
        window1.create(new VideoMode(WINDOW_W ,WINDOW_H), "fenetre JSFML");//-1 = fullscreen
        window1.setFramerateLimit(60);
        window1.setKeyRepeatEnabled(true);
        //   window1.setMouseCursorVisible(false);

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
        Vector2i pos =new Vector2i(0,0);
        test.setOrigin(test.getLocalBounds().width/2, test.getLocalBounds().height/2);
        test.setPosition(window1.getSize().x/2,window1.getSize().y/2);

        int nbrChicken = 20;
        EntityManager manager = EntityManager.getIntance();
        Player player = Player.getInstance();
        manager.addEntity(player);
        for (int i=0; i<nbrChicken; ++i){
            try {
                manager.addEntity(new Mob(i, new SeekMove(player), 10));
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        TextureManager texManager= new TextureManager(manager.getEntityList());

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
               /* if (event.type == Event.Type.KEY_PRESSED) {
                    event.asKeyEvent();
                    if (Keyboard.isKeyPressed(Keyboard.Key.LEFT))
                        textureManager1.updateTexture(test, 2);
                    if (Keyboard.isKeyPressed(Keyboard.Key.RIGHT))
                        textureManager1.updateTexture(test,3);
                    if (Keyboard.isKeyPressed(Keyboard.Key.UP))
                        textureManager1.updateTexture(test,4);
                    if (Keyboard.isKeyPressed(Keyboard.Key.DOWN))
                        textureManager1.updateTexture(test,1);
                }*/
                if (event.type == Event.Type.MOUSE_MOVED) {
                    event.asMouseEvent();
                    pos = Mouse.getPosition(window1);
                    System.out.println(pos.x + " " + pos.y);
                }
            }

            if(pos.x+pos.y>=window1.getSize().x){
                if(window1.getSize().x-pos.x+pos.y>=window1.getSize().x){
                    //textureManager1.updateTexture(test,1);
                   player.setDirection(1);
                }
                else{
                    //textureManager1.updateTexture(test,3);
                    player.setDirection(3);
                }
            }
            if(pos.x+pos.y<=window1.getSize().x) {
                if (window1.getSize().x - pos.x + pos.y <= window1.getSize().x) {
                   // textureManager1.updateTexture(test, 4);
                    player.setDirection(4);
                }
                else{
                    //textureManager1.updateTexture(test,2);
                    player.setDirection(2);
                }
            }

          /*  if(pos.y!=window1.getSize().y/2){
                float angle = (float)Math.atan((pos.x-window1.getSize().x/2)/(pos.y-window1.getSize().y/2));
                test.setRotation((float)Math.toRadians(angle));
                System.out.println();
            }*/

            window1.draw(player);

            window1.display();
            window1.clear(BLACK);
        }


    }


}