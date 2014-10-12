package Screens;

import static org.jsfml.graphics.Color.BLACK;
import static org.jsfml.graphics.Color.WHITE;


import java.util.ArrayList;


import Entities.*;

import Graphics.TextureManager;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Mouse;
import org.jsfml.window.event.Event;

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

        try {
            PlayerSpawnManager.placement((Player) EntityManager.getEntity("Player", 0), App);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }


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
                    try {
                        ((Player)EntityManager.getEntity("Player", 0)).fire();
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }

            // Draw and update viewfinder
            ViewFinder viewFinder = new ViewFinder();
            viewFinder.updateViewFinder(pos, Player.getInstance());
            App.draw(viewFinder);

            // Draw and update Game entity
            for(GameBaseEntity it : manager.getEntityList()) {
                if(it.getPosition().x < 0 || it.getPosition().y < 0 || it.getPosition().x > App.getSize().x || it.getPosition().y > App.getSize().y) {
                    it.setVisible(false);
                }
                if(!(it instanceof Player)) {
                    try {
                        ((MovableEntity) it).moveEntity();
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                if(it instanceof Player) {
                    Player.getInstance().updateDirection(pos, App.getSize());
                }
                if(it instanceof Bullet) {
                    ((Bullet) it).detectCollision();
                }
                texManager.updateTexture(it, it.getId(), it.getDirection());
                App.draw(it);
            }

            for(int i = 0 ; i<manager.getEntityList().size() ; i++) {
                if(!manager.getEntityList().get(i).isVisible()) {
                    System.out.println("Delete bullet");
                    manager.getEntityList().remove(i);
                }
                else if(!manager.getEntityList().get(i).isAlive() && manager.getEntityList().get(i) instanceof Mob) {
                    ((Mob) manager.getEntityList().get(i)).decDieAnim();
                }
            }

            App.display();
            App.clear(BLACK);
        }
        return -1;//exit
    }
}





















































































