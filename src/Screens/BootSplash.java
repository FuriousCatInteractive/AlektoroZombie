package Screens;

import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundBuffer;
import org.jsfml.graphics.*;
import org.jsfml.window.Keyboard;
import org.jsfml.window.event.Event;

import java.io.IOException;
import java.nio.file.Paths;

public class BootSplash extends cScreen{

    private int alpha_max;
    private int alpha_div = 1;
    private boolean playing;

    private Sprite cat;
    private Texture maTexture;
    private float ratio;
    private RenderWindow mafenetre;
    private float alpha;
    private boolean ok;
    private boolean ok2;

    public void BootSplashInit(RenderWindow fenetre){

        mafenetre = fenetre;
        maTexture = new Texture(); // déclaration d'une texture
        cat = new Sprite();

        try {
            maTexture.loadFromFile(Paths.get("rsc/img/logo-furious-cat-interactive.png")); // on charge la texture qui se trouve dans notre dossier assets
        } catch (IOException e) {
            e.printStackTrace();
        }

        cat.setTexture(maTexture); // on applique la texture à notre sprite
        ratio = (float)mafenetre.getSize().y/(maTexture.getSize().y*2);
        cat.setScale(ratio, ratio);


        float textureX = maTexture.getSize().x*ratio/2;
        float textureY = maTexture.getSize().y*ratio/2;

        float posX = mafenetre.getSize().x/2;
        float posY = mafenetre.getSize().y/2;

        //System.out.println(posX +" "+ posY);

        cat.setOrigin(maTexture.getSize().x / 2, maTexture.getSize().y / 2);
        cat.setPosition(posX, posY);

        System.out.println("origin= " + cat.getOrigin().x+" "+ cat.getOrigin().y);
        System.out.println("position = " + cat.getPosition().x +" "+cat.getPosition().y);

        alpha=0;
        ok = false;
        ok2=false;
    }

    public int Run(RenderWindow App){

        boolean Running = true;
        BootSplashInit(App);
        long debut_bootsplash = System.currentTimeMillis();
        int duree=5000;

        while (System.currentTimeMillis()-debut_bootsplash<duree && Running)
        {
            //Verifying events
            for (Event event : App.pollEvents()) {
                {
                    // Window closed
                    if (event.type == event.type.CLOSED)
                    {
                        return (-1);
                    }
                    if(event.type == Event.Type.MOUSE_BUTTON_RELEASED) {
                        return 7;
                    }
                    //Key pressed
                    if (event.type == Event.Type.KEY_PRESSED)
                    {
                        event.asKeyEvent();

                        if (Keyboard.isKeyPressed(Keyboard.Key.ESCAPE))
                            return (-1);
                        else
                        {

                            return 7;
                        }
                    }
                }
            }

            update(cat);
            App.draw(cat);
            App.display();

            //Clearing screen
            App.clear();
        }

        //Never reaching this point normally, but just in case, exit the application
        System.out.println("bootsplash finit");
        return (7);
    }

    public void update(Sprite cat)
    {
        if(alpha<=255 && !ok)
            alpha+=2f;
        else if(alpha>=0)
        {
            ok = true;
            alpha-=2f;
        }
        cat.setColor(new Color(255, 255, 255, (int) alpha)); // half transparent

        if(cat.getRotation()<360)
        {
            cat.rotate(3f);
            ok2 = true;
        }
        else if(ok2==true)
        {
            this.startMusic("rsc/sound/Meow.ogg");
            ok2=false;
        }
    }


    private void centerSprite(){
        float posX = maTexture.getSize().x*ratio/2;
        float posY = maTexture.getSize().y*ratio/2;
        cat.setPosition(posX, posY);

    }

    private void startMusic(String path)
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
        //sound.setVolume(40.0f);
    }
}
