package Screens;

import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundBuffer;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Mouse;
import org.jsfml.window.event.Event;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by coco on 14-10-11.
 */
public class SelectMusic extends cScreen{
    private int alpha_max;
    private int alpha_div = 1;
    private boolean playing;
    Sound sound;

    public int Run(RenderWindow App){

        boolean Running = true;
        int alpha = 0;
        Font Font = new Font();
        Text Menu1 = new Text();
        Text Menu2 = new Text();
        Text retour = new Text();
        Text Titre = new Text();


        int menu = 0;


        try {
            Font.loadFromFile(Paths.get("rsc/font/Frank Knows.ttf"));
        } catch (IOException e) {
            e.printStackTrace();
            return (-1);
        }

        int taille_Font = 50;

        Titre.setFont(Font);
        Titre.setCharacterSize((int)(1.40*taille_Font));
        Titre.setString("Selection des musiques ");
        Titre.setPosition( App.getSize().x/2-Titre.getLocalBounds().width/2, 20);

        Menu1.setFont(Font);
        Menu1.setCharacterSize(taille_Font);
        Menu1.setString("Musique #1");
        Menu1.setPosition( App.getSize().x/2-Menu1.getLocalBounds().width/2, App.getSize().y/3);

        Menu2.setFont(Font);
        Menu2.setCharacterSize(taille_Font);
        Menu2.setString("Musique #2");
        Menu2.setPosition(  App.getSize().x/2-Menu2.getLocalBounds().width/2, App.getSize().y/3+1*taille_Font+20);

        retour.setFont(Font);
        retour.setCharacterSize(taille_Font);
        retour.setString("Retour");
        retour.setPosition(  App.getSize().x/2-retour.getLocalBounds().width/2, App.getSize().y/2+2*taille_Font);


        Vector2i pos = new Vector2i(0,0);
        startMusic("rsc/sound/son_poules_menus.wav");
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
                            //System.out.println("menu1");

                        }
                        else if(Menu2.getGlobalBounds().contains((float)pos.x, (float)pos.y)){
                            menu = 1;
                            // System.out.println("menu0");
                        }
                        else if(retour.getGlobalBounds().contains((float)pos.x, (float)pos.y)){
                            menu = 2;
                            // System.out.println("menu0");
                        }
                    }

                    //clic de la souris
                    if (event.type == Event.Type.MOUSE_BUTTON_PRESSED) {
                        event.asMouseEvent();
                        if (menu == 0) {
                            sound.stop();
                            return (3);
                        }
                        else if (menu == 1){
                            //Let's get work...
                            sound.stop();
                            return (3);
                        }
                        else if (menu == 2){
                            return 1;
                        }

                    }

                    //Key pressed
                    else if (event.type == Event.Type.KEY_PRESSED)
                    {
                        event.asKeyEvent();

                        if (Keyboard.isKeyPressed(Keyboard.Key.ESCAPE))
                            return 1;

                        if (Keyboard.isKeyPressed(Keyboard.Key.DOWN)){
                            menu++;
                            if(menu>2)
                                menu = 0;
                        }

                        if (Keyboard.isKeyPressed(Keyboard.Key.UP)) {
                            menu--;
                            if(menu<0)
                                menu = 2;
                        }


                        if (Keyboard.isKeyPressed(Keyboard.Key.RETURN)) {
                            sound.stop();
                            if (menu == 0) {

                                return (3);
                            } else if (menu == 1) {
                                //Let's get work...

                                return (3);}
                            else if (menu == 2){

                                return 1;
                            }
                        }
                    }
                }
            }

            if (menu == 0)
            {
                Menu1.setColor(Color.RED);
                Menu2.setColor(Color.WHITE);
                retour.setColor(Color.WHITE);
            }
            else if (menu == 1)
            {
                Menu1.setColor(Color.WHITE);
                Menu2.setColor(Color.RED);
                retour.setColor(Color.WHITE);
            }
            else if (menu == 2)
            {
                Menu1.setColor(Color.WHITE);
                Menu2.setColor(Color.WHITE);
                retour.setColor(Color.RED);
            }


            App.clear();
            App.draw(Titre);
            App.draw(retour);
            App.draw(Menu1);
            App.draw(Menu2);
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
