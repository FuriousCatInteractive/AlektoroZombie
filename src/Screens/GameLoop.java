package Screens;

import static org.jsfml.graphics.Color.BLACK;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;


import Entities.*;

import Graphics.TextureManager;

import org.jsfml.graphics.*;
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

        //background
        Sprite background = new Sprite();
        try {
            Texture maTexture = new Texture();
            maTexture.loadFromFile(Paths.get("rsc/img/background.jpg")); // on charge la texture qui se trouve dans notre dossier assets
            background.setTexture(maTexture); // on applique la texture à notre sprite
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Vars pour affichage des points de vie du joueur
        Text playerHealthStatus = new Text();
        Font Font = new Font();
        int taille_Font = 15;
        try {
            Font.loadFromFile(Paths.get("rsc/font/Frank Knows.ttf"));
        } catch (IOException e) {
            e.printStackTrace();
            return (-1);
        }

        Vector2i pos = new Vector2i(0, 0);

        EntityManager manager = EntityManager.getIntance();

        PlacePlayer PlayerSpawnManager = new PlacePlayer();
        EntityManager.addEntity(Player.getInstance());

        PlaceMobs MobsSpawnManager = new PlaceMobs(Player.getInstance(), "rsc/sound/Zelda3.serial");
        ArrayList<Mob> listeMobs = MobsSpawnManager.getMobsList();
        for (int i=0; i<listeMobs.size(); i++){
            try {
                EntityManager.addEntity(listeMobs.get(i));
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        TextureManager texManager= new TextureManager(EntityManager.getEntityList());

        try {
            PlayerSpawnManager.placement(EntityManager.getEntity("Player", 0), App);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }


        while (App.isOpen()){

            if (Keyboard.isKeyPressed(Keyboard.Key.ESCAPE)) {
                return 2;
            }
            // On gère les événements
            for (Event event : App.pollEvents()) {
                if (event.type == Event.Type.CLOSED) {
                    // Si l'utilisateur clique sur la croix rouge alors on ferme
                    // la fenêtre
                    return (-1);
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
            for(GameBaseEntity it : EntityManager.getEntityList()) {
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
                    it.detectCollision();
                }
                else if(it instanceof Player) {
                    it.detectCollision();
                }
                TextureManager.updateTexture(it, it.getId(), it.getDirection());
                App.draw(it);
            }


            //Affichage des points de vie restants
            playerHealthStatus.setFont(Font);
            playerHealthStatus.setCharacterSize((int)(1.50*taille_Font));
            playerHealthStatus.setString("Points de vie restants : "+Player.getInstance().getHealthPoints());
            playerHealthStatus.setPosition(App.getSize().x - playerHealthStatus.getLocalBounds().width - 6, App.getSize().y - playerHealthStatus.getLocalBounds().height - 6);
            App.draw(playerHealthStatus);

            for(int i = 0 ; i<EntityManager.getEntityList().size() ; i++) {
                if(!EntityManager.getEntityList().get(i).isVisible()) {
                    System.out.println("Delete bullet");
                    EntityManager.getEntityList().remove(i);
                }
                else if(!EntityManager.getEntityList().get(i).isAlive() && EntityManager.getEntityList().get(i) instanceof Mob) {
                    ((Mob) EntityManager.getEntityList().get(i)).decDieAnim();
                }
            }

            App.display();
            App.draw(background);
        }
        return -1;//exit
    }
}





















































































