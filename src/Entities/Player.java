package Entities;

import Graphics.TextureManager;
import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundBuffer;
import org.jsfml.graphics.Color;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

import java.io.IOException;
import java.nio.file.Paths;

import static org.jsfml.graphics.Color.WHITE;

/**
 * Created by steven on 10/10/14.
 */
public class Player extends MovableEntity{
    private Vector2f vectorViewFinder;
    private static int bulletId = 0;
    private int healthPoints;

    static Player singleton = null;

    private Player() {
        super();
        reset();
    }

    public final static Player getInstance() {
        if(singleton == null) {
            singleton = new Player();
        }
        return singleton;
    }

    public void updateDirection(Vector2i pos, Vector2i sizeWindow) {
        if(pos.x+pos.y>=sizeWindow.x){
            if(sizeWindow.x-pos.x+pos.y>=sizeWindow.x){
                this.setDirection(1);
            }
            else{
                this.setDirection(3);
            }
        }
        if(pos.x+pos.y<=sizeWindow.x) {
            if (sizeWindow.x - pos.x + pos.y <= sizeWindow.x) {
                this.setDirection(4);
            }
            else{
                this.setDirection(2);
            }
        }
    }

    public void fire() {
        EntityManager.addEntity(new Bullet(this, bulletId));
        try {
            TextureManager.mergeTextureSprite(EntityManager.getEntity("Bullet", bulletId));
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        ++bulletId;
    }

    @Override
    public void touch() {
        Sound s=startMusic("rsc/sound/lancer_pierre.wav");
        s.setVolume(400f);
        --healthPoints;
        if(healthPoints<0)
            healthPoints=0;
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
        Sound sound = new Sound();
        sound.setBuffer(soundBuffer);
        sound.play();

        return sound;
    }



   


    @Override
    public void detectCollision() {
        for(int i=0; i<EntityManager.getEntityList().size(); ++i) {
            if(EntityManager.getEntityList().get(i) instanceof Mob) {
                if(this.getGlobalBounds().intersection(EntityManager.getEntityList().get(i).getGlobalBounds()) != null) {
                    touch();
                    EntityManager.getEntityList().get(i).touch();
                }
            }
        }
    }

    public Vector2f getVectorViewFinder() {
        return vectorViewFinder;
    }

    public void setVectorViewFinder(Vector2f vectorViewFinder) {
        this.vectorViewFinder = vectorViewFinder;
    }

    public int getHealthPoints()
    {
        return healthPoints;
    }

    public void reset(){
        healthPoints=3;
        id = 0;
        this.setPosition(50, 50);
        this.vectorViewFinder = new Vector2f(0,0);
    }
}