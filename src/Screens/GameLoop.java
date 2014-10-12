package Screens;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;


import Entities.*;

import Graphics.TextureManager;

import World.Score;
import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundBuffer;
import org.jsfml.graphics.*;
import org.jsfml.system.Clock;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Mouse;
import org.jsfml.window.event.Event;

import GraphicsEntities.ViewFinder;
import PlaceEntities.PlaceMobs;
import PlaceEntities.PlacePlayer;

import static org.jsfml.graphics.Color.*;

/**
 * Created by coco on 14-10-11.
 */
public class GameLoop extends cScreen {

    Clock gameClock;
    Sound sound;

    public int Run(RenderWindow App) {

        startMusic("rsc/sound/son_poules_menus.wav");

        Sprite viseur = loadViseur(App);

        CircleShape circle = new CircleShape(200);
        circle.setOutlineColor(Color.GREEN);
        circle.setFillColor(Color.TRANSPARENT);
        circle.setOutlineThickness(3);
        circle.setPosition(App.getSize().x/2-200,App.getSize().y/2-200);
        //  circle.setOrigin(-155,-170);
        circle.setPointCount(50);
        float posCirclex=circle.getPosition().x;
        float posCircley=circle.getPosition().y;


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
        Text scoreStatus = new Text();
        Font Font = new Font();
        int taille_Font = 20;
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

        PlaceMobs MobsSpawnManager = new PlaceMobs("rsc/sound/Zelda3.serial");
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
        gameClock =new Clock();
        gameClock.restart();

        startMusic("rsc/sound/ZeldaDance.ogg");
        while (App.isOpen()){


            Mob tempMonstre;
            long currentTime=gameClock.getElapsedTime().asMilliseconds();
            if (currentTime<0) currentTime=0;
            //TOCHANGE
            if(MobsSpawnManager.nextEnemy(false,currentTime)!=null)
                try {
                    tempMonstre=MobsSpawnManager.nextEnemy(true,currentTime);
                    // ((Mob) manager.getEntity("Mob", manager.getEntityList().indexOf(tempMonstre))).setMovable(true);
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            if (Keyboard.isKeyPressed(Keyboard.Key.ESCAPE)) {
                sound.stop();
                return 1;
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
                        //Sound s1=  startMusic("rsc/sound/lancer_pierre.wav");
                       // s1.setVolume(40.0f);

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
                if((it.getPosition().x < 0 || it.getPosition().y < 0 || it.getPosition().x > App.getSize().x || it.getPosition().y > App.getSize().y) && it instanceof Bullet) {
                    Score.dec(2);
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
                else if (it instanceof Mob){
                    Vector2i posMOb = new Vector2i((int)it.getPosition().x, (int)it.getPosition().y);
                    ((Mob)(it)).updateDirection(posMOb, App.getSize());

                    boolean intersect = false;
                    FloatRect rectMob = new FloatRect(it.getGlobalBounds().left+9,it.getGlobalBounds().top+9,
                            it.getGlobalBounds().width-9,it.getGlobalBounds().height-9);
                    for(int cpt =0; cpt<circle.getPointCount();cpt++){

                        if(rectMob.contains(circle.getPoint(cpt).x + posCirclex, circle.getPoint(cpt).y + posCircley)) {
                           // System.out.println("caca" + circle.getPoint(cpt) + "+ " + circle.getPosition());
                            intersect=true;
                        }
                    }
                    if(intersect)
                        it.setColor(GREEN);
                    else
                        it.setColor(WHITE);
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
            playerHealthStatus.setPosition(App.getSize().x - playerHealthStatus.getLocalBounds().width -15, App.getSize().y - playerHealthStatus.getLocalBounds().height - 15);
            App.draw(playerHealthStatus);

            scoreStatus.setFont(Font);
            scoreStatus.setCharacterSize((int)(1.50*taille_Font));
            scoreStatus.setString("Score : "+ Score.getScore());
            scoreStatus.setPosition(scoreStatus.getLocalBounds().width -15, App.getSize().y - scoreStatus.getLocalBounds().height - 20);
            App.draw(scoreStatus);

            for(int i = 0 ; i<EntityManager.getEntityList().size() ; i++) {
                if(!EntityManager.getEntityList().get(i).isVisible()) {
                    if(EntityManager.getEntityList().get(i) instanceof Mob) {
                        Score.incKillChicken();
                    }
                    EntityManager.getEntityList().remove(i);
                }
                else if(!EntityManager.getEntityList().get(i).isAlive() && EntityManager.getEntityList().get(i) instanceof Mob) {
                    ((Mob) EntityManager.getEntityList().get(i)).decDieAnim();
                    //Sound s2=startMusic("rsc/sound/poulet_cri_attenue.wav");
                   // s2.setVolume(2.0f);
                }
            }


            try {
                boolean win = true;
                if (((Player) EntityManager.getEntity("Player", 0)).getHealthPoints() <= 0) {
                    ((Player) EntityManager.getEntity("Player", 0)).reset();
                    EntityManager.getEntityList().clear();
                    sound.stop();
                    startMusic("rsc/sound/zombie_agonie.wav");
                    return 4;
                }
                else {
                    for(GameBaseEntity it : EntityManager.getEntityList()) {
                        if(it instanceof Mob && it.isVisible() == true) {
                            win = false;
                        }
                    }
                    //victoire
                    if(win == true) {
                        sound.stop();
                        return 5;
                    }
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }

            Vector2f posViseur= new Vector2f((float)pos.x, (float)pos.y);
            viseur.setPosition(posViseur);
            App.draw(viseur);
            App.display();
            App.draw(background);
            App.draw(circle);
        }
        return -1;//exit
    }

    private Sound startMusic(String path)
    {
        SoundBuffer soundBuffer = new SoundBuffer();
        try {
            soundBuffer.loadFromFile(Paths.get(path));
            System.out.println("Sound duration: " + soundBuffer.getDuration().asSeconds() + " seconds");
        } catch(IOException ex) {
            //Something went wrong
            System.err.println("Failed to load the sound:");
            ex.printStackTrace();
        }

        //Create a sound and set its buffer
        sound = new Sound();
        sound.setBuffer(soundBuffer);
        sound.play();

        return sound;
    }
}





















































































