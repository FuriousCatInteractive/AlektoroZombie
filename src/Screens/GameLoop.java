package Screens;

import static org.jsfml.graphics.Color.BLACK;

import java.util.ArrayList;

import Graphics.TextureManager;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Mouse;
import org.jsfml.window.event.Event;

import Entities.EntityManager;
import Entities.GameBaseEntity;
import Entities.Mob;
import Entities.Player;
import GraphicsEntities.ViewFinder;
import PlaceEntities.PlaceMobs;
import PlaceEntities.PlacePlayer;

/**
 * Created by coco on 14-10-11.
 */
public class GameLoop extends cScreen {

    public int Run(RenderWindow App) {

        Vector2i pos = new Vector2i(0, 0);

        EntityManager manager = EntityManager.getIntance();

        PlacePlayer PlayerSpawnManager = new PlacePlayer();
        manager.addEntity(Player.getInstance());

        PlaceMobs MobsSpawnManager = new PlaceMobs(Player.getInstance(), "rsc/sound/Zelda3.serial");
        ArrayList<Mob> listeMobs = MobsSpawnManager.getMobsList();
        for (int i=0; i<listeMobs.size(); i++){
            try {
                manager.addEntity(listeMobs.get(i));
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        TextureManager texManager= new TextureManager(manager.getEntityList());

        while (App.isOpen()){

            if (Keyboard.isKeyPressed(Keyboard.Key.ESCAPE)) {
                App.close();
            }
            // On gère les événements
            for (Event event : App.pollEvents()) {
                if (event.type == Event.Type.CLOSED) {
                    // Si l'utilisateur clique sur la croix rouge alors on ferme
                    // la fenêtre
                    App.close();
                }

                if (event.type == Event.Type.MOUSE_MOVED) {
                    event.asMouseEvent();
                    pos = Mouse.getPosition(App);
                    System.out.println(pos.x + " " + pos.y);
                }
                //clic de la souris
                if (event.type == Event.Type.MOUSE_BUTTON_PRESSED) {
                    event.asMouseEvent();
                    System.out.println("clic!!");
                }
            }

            // Draw and update viewfinder
            ViewFinder viewFinder = new ViewFinder();
            viewFinder.updateViewFinder(pos, Player.getInstance());
            App.draw(viewFinder);

            // Draw and update Game entity
            for(GameBaseEntity it : manager.getEntityList()) {
                if(it instanceof Mob) {
                    try {
                        ((Mob) it).moveEntity();
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                if(it instanceof Player) {
                    Player.getInstance().updateDirection(pos, App.getSize());
                }
                texManager.updateTexture(it, it.getId(), it.getDirection());
                App.draw(it);
            }

            App.display();
            App.clear(BLACK);
        }
        return -1;//exit
    }
}