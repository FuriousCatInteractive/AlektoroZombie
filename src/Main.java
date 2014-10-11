import Entities.*;
import Graphics.TextureManager;
import GraphicsEntities.ViewFinder;
import MoveBehavior.SeekMove;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Mouse;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

import static org.jsfml.graphics.Color.*;

public class Main {

    public static void main(String[] args) {

        int WINDOW_H = 700;
        int WINDOW_W = 700;

        GameBaseEntity toKill = null;

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


        Vector2i pos =new Vector2i(0,0);

        int nbrChicken = 20;
        EntityManager manager = EntityManager.getIntance();
        Player player = Player.getInstance();
        manager.addEntity(player);
        for (int i=0; i<nbrChicken; ++i){
            try {
                manager.addEntity(new Mob(i, new SeekMove(player), 1));
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        TextureManager texManager= new TextureManager(manager.getEntityList());
        player.setOrigin(player.getLocalBounds().width/2, player.getLocalBounds().height/2);
        player.setPosition(window1.getSize().x/2,window1.getSize().y/2);

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

                if (event.type == Event.Type.MOUSE_MOVED) {
                    event.asMouseEvent();
                    pos = Mouse.getPosition(window1);
                    System.out.println(pos.x + " " + pos.y);
                }
                //clic de la souris
                if (event.type == Event.Type.MOUSE_BUTTON_PRESSED) {
                    event.asMouseEvent();
                    player.fire();
                }
            }

            // Draw and update viewfinder
            ViewFinder viewFinder = new ViewFinder();
            viewFinder.updateViewFinder(pos, player);

            window1.draw(viewFinder);

            // Draw and update Game entity
            for(GameBaseEntity it : manager.getEntityList()) {
                if(it.getPosition().x < 0 || it.getPosition().y < 0 || it.getPosition().x > window1.getSize().x || it.getPosition().y > window1.getSize().y) {
                    it.setVisible(false);
                }
                if (!(it instanceof Player)) {
                    try {
                        ((MovableEntity) it).moveEntity();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                if (it instanceof Player) {
                    player.updateDirection(pos, window1.getSize());
                }
                texManager.updateTexture(it, it.getId(), it.getDirection());
                 window1.draw(it);
            }

            for(int i = 0 ; i<manager.getEntityList().size() ; i++) {
                if(manager.getEntityList().get(i) instanceof Bullet && !manager.getEntityList().get(i).isVisible()) {
                    System.out.println("Delete bullet");
                    manager.getEntityList().remove(i);
                }
            }

            window1.display();
            window1.clear(BLACK);
        }
    }
}