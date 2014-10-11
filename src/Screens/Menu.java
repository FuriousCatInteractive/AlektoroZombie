package Screens;

import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundBuffer;
import org.jsfml.graphics.*;
import org.jsfml.window.Keyboard;
import org.jsfml.window.event.Event;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by coco on 14-10-11.
 */
public class Menu extends cScreen{
    private int alpha_max;
    private int alpha_div = 1;
    private boolean playing;

    public int Run(RenderWindow App){

        boolean Running = true;
        Texture Texture = new Texture();
        Sprite Sprite = new Sprite();
        int alpha = 0;
        Font Font = new Font();
        Text Menu1 = new Text();
        Text Menu2 = new Text();
        Text Menu3 = new Text();
        Text Titre = new Text();

        int menu = 0;

        try {
            Texture.loadFromFile(Paths.get("rsc/img/fond_menu.png"));
        } catch (IOException e) {
            e.printStackTrace();
            return (-1);
        }


        Sprite.setTexture(Texture);
        //  Sprite.setColor(new Color(255, 255, 255, alpha));
        try {
            Font.loadFromFile(Paths.get("rsc/font/Volter__28Goldfish_29.ttf"));
        } catch (IOException e) {
            e.printStackTrace();
            return (-1);
        }

        int taille_Font = 40;

        Titre.setFont(Font);
        Titre.setCharacterSize((int)1.30*taille_Font);
        Titre.setString("AlektoroZombie ");
        Titre.setPosition( App.getSize().x/2-Titre.getLocalBounds().width/2, 20);



        Menu1.setFont(Font);
        Menu1.setCharacterSize(taille_Font);
        Menu1.setString("Play");
        Menu1.setPosition( App.getSize().x/2-Menu1.getLocalBounds().width/2, App.getSize().y/2);

        Menu2.setFont(Font);
        Menu2.setCharacterSize(taille_Font);
        Menu2.setString("Exit");
        Menu2.setPosition(  App.getSize().x/2-Menu2.getLocalBounds().width/2, App.getSize().y/2+2*taille_Font);

        Menu3.setFont(Font);
        Menu3.setCharacterSize(taille_Font);
        Menu3.setString("Continue");
        Menu3.setPosition(  App.getSize().x/2-Menu3.getLocalBounds().width/2, App.getSize().y/2+4*taille_Font);

        /*if (playing)
        {
            alpha = alpha_max;
        }*/
        //startMusic("rsc/sound/king.it.ogg");

        while (Running)
        {
            //Verifying events
            for (Event event : App.pollEvents()) {
                {
                    // Window closed
                    if (event.type == event.type.CLOSED)
                    {
                        return (-1);
                    }
                    //Key pressed
                    if (event.type == Event.Type.KEY_PRESSED)
                    {
                        event.asKeyEvent();

                        if (Keyboard.isKeyPressed(Keyboard.Key.ESCAPE))
                            return  -1;

                        if (Keyboard.isKeyPressed(Keyboard.Key.UP))
                            menu = 0;


                        if (Keyboard.isKeyPressed(Keyboard.Key.DOWN))
                            menu = 1;

                        if (Keyboard.isKeyPressed(Keyboard.Key.RETURN)) {
                            if (menu == 0) {
                                //Let's get play !
                                playing = true;
                                return (2);
                            } else {
                                //Let's get work...
                                return (-1);
                            }
                        }
                    }
                }
            }
            //When getting at alpha_max, we stop modifying the sprite
           /* if (alpha<alpha_max)
            {
                alpha++;
            }
            Sprite.setColor(new Color(255, 255, 255, alpha / alpha_div));*/
            if (menu == 0)
            {
                Menu1.setColor(new Color(255, 0, 0, 255));
                Menu2.setColor(new Color(255, 255, 255, 255));
                Menu3.setColor(new Color(255, 0, 0, 255));
            }
            else
            {
                Menu1.setColor(new Color(255, 255, 255, 255));
                Menu2.setColor(new Color(255, 0, 0, 255));
                Menu3.setColor(new Color(255, 255, 255, 255));
            }

            //Clearing screen
            App.clear();
            //Drawing
            //App.draw(Sprite);
            App.draw(Titre);
            if (alpha == alpha_max)
            {
                if (playing)
                {
                    App.draw(Menu3);
                }
                else
                {
                    App.draw(Menu1);
                }
                App.draw(Menu2);
            }
            App.display();
        }

        //Never reaching this point normally, but just in case, exit the application
        return (-1);
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
