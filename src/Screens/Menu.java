package Screens;

import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundBuffer;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Mouse;
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
    Sound sound;

    public int Run(RenderWindow App){

        boolean Running = true;
        Texture Texture = new Texture();
        Sprite Sprite = new Sprite();
        Sprite background = new Sprite();
        int alpha = 0;
        Font Font = new Font();
        Text Menu1 = new Text();
        Text Menu2 = new Text();
        Text Menu3 = new Text();
        Text Titre = new Text();
        Text bottomText = new Text();


        int menu = 0;

        try {
            Texture maTexture = new Texture();
            maTexture.loadFromFile(Paths.get("rsc/img/fond_menu.png")); // on charge la texture qui se trouve dans notre dossier assets
            background.setTexture(maTexture); // on applique la texture à notre sprite
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*try {
            Texture.loadFromFile(Paths.get("rsc/img/fond_menu.png"));
        } catch (IOException e) {
            e.printStackTrace();
            return (-1);
        }*/


        Sprite.setTexture(Texture);
        background.setPosition(App.getSize().x/2-background.getLocalBounds().width/2,
                160f);
        //  Sprite.setColor(new Color(255, 255, 255, alpha));
        try {
            Font.loadFromFile(Paths.get("rsc/font/Frank Knows.ttf"));
        } catch (IOException e) {
            e.printStackTrace();
            return (-1);
        }

        int taille_Font = 70;

        Titre.setFont(Font);
        Titre.setCharacterSize((int)(1.50*taille_Font));
        Titre.setString("AlektoroZombie ");
        Titre.setPosition( App.getSize().x/2-Titre.getLocalBounds().width/2, 20);

        bottomText.setFont(Font);
        bottomText.setCharacterSize((int)(0.30*taille_Font));
        bottomText.setString("Furious Cat Interactive - WonderJam oct 2014 ");
        bottomText.setPosition( App.getSize().x/2-bottomText.getLocalBounds().width/2, App.getSize().y-bottomText.getLocalBounds().height-20);

        Menu1.setFont(Font);
        Menu1.setCharacterSize(taille_Font);
        Menu1.setString("Play");
        Menu1.setPosition( App.getSize().x/2-Menu1.getLocalBounds().width/2, App.getSize().y/2+70);

        Menu2.setFont(Font);
        Menu2.setCharacterSize(taille_Font);
        Menu2.setString("Exit");
        Menu2.setPosition(  App.getSize().x/2-Menu2.getLocalBounds().width/2, App.getSize().y/2+2*taille_Font);

        Vector2i pos = new Vector2i(0,0);
        startMusic("rsc/sound/king.it.ogg");

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

                    if (event.type == Event.Type.MOUSE_MOVED) {
                        event.asMouseEvent();
                        pos = Mouse.getPosition(App);

                        if(Menu1.getGlobalBounds().contains((float)pos.x, (float)pos.y)){
                            menu = 0;
                        }
                        else if(Menu2.getGlobalBounds().contains((float)pos.x, (float)pos.y)){
                            menu = 1;
                        }
                    }

                    //clic de la souris
                    if (event.type == Event.Type.MOUSE_BUTTON_PRESSED) {
                        event.asMouseEvent();
                        if (menu == 0) {
                            //Let's get play !
                            playing = true;
                            sound.stop();
                            return (2);
                        } else {
                            //Let's get work...
                            sound.stop();
                            return (-1);
                        }
                    }

                    //Key pressed
                    else if (event.type == Event.Type.KEY_PRESSED)
                    {
                        event.asKeyEvent();

                        if (Keyboard.isKeyPressed(Keyboard.Key.ESCAPE))
                            return  -1;

                        if (Keyboard.isKeyPressed(Keyboard.Key.UP)){
                            menu = 0;
                        }

                        if (Keyboard.isKeyPressed(Keyboard.Key.DOWN)) {
                            menu = 1;
                        }


                        if (Keyboard.isKeyPressed(Keyboard.Key.RETURN)) {
                            sound.stop();
                            if (menu == 0) {

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


            App.clear();
            App.draw(Titre);
            App.draw(Menu1);
            App.draw(Menu2);
            App.draw(background);
            App.draw(bottomText);
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
        sound = new Sound();
        sound.setBuffer(soundBuffer);
        sound.play();
        //sound.setVolume(40.0f);
    }

}
