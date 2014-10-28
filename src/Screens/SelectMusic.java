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
public class SelectMusic extends cScreen{
    private int alpha_max;
    private int alpha_div = 1;
    private boolean playing;
    Sound sound;
    private static int speedPlayerChoice = 1;
    private static int musicPlayerChoice = 1;

    public int Run(RenderWindow App){

        boolean Running = true;
        int alpha = 0;
        Font Font = new Font();
        Text Menu1 = new Text();
        Text Menu2 = new Text();
        Text retour = new Text();
        Text Titre = new Text();
        Text vitesse1 = new Text();
        Text vitesse2 = new Text();
        Text vitesse3 = new Text();
        Text vitesse4 = new Text();
        Text vitesse5 = new Text();
        Text vitesseLabel = new Text();
        Text start = new Text();


        int menu = 0;
        int vitesse = 0;
        int select = 0;


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
        Menu1.setPosition( App.getSize().x/2-Menu1.getLocalBounds().width/2, App.getSize().y/4);

        Menu2.setFont(Font);
        Menu2.setCharacterSize(taille_Font);
        Menu2.setString("Musique #2");
        Menu2.setPosition(  App.getSize().x/2-Menu2.getLocalBounds().width/2, App.getSize().y/4+taille_Font);

        start.setFont(Font);
        start.setCharacterSize(taille_Font+20);
        start.setString("START");
        start.setPosition(  App.getSize().x/2-start.getLocalBounds().width/2, App.getSize().y-4*taille_Font);

        retour.setFont(Font);
        retour.setCharacterSize(taille_Font-20);
        retour.setString("Retour");
        retour.setPosition(  App.getSize().x/2-retour.getLocalBounds().width/2, App.getSize().y-2*taille_Font);

        vitesseLabel.setFont(Font);
        vitesseLabel.setCharacterSize(taille_Font);
        vitesseLabel.setString("Vitesse");
        vitesseLabel.setPosition(App.getSize().x/2-vitesseLabel.getLocalBounds().width/2, (App.getSize().y/4)+3*taille_Font);

        vitesse1.setFont(Font);
        vitesse1.setCharacterSize(taille_Font);
        vitesse1.setString("x1");
        vitesse1.setPosition( App.getSize().x/5-vitesse1.getLocalBounds().width/2, (App.getSize().y/4)+4*taille_Font);

        vitesse2.setFont(Font);
        vitesse2.setCharacterSize(taille_Font);
        vitesse2.setString("x2");
        vitesse2.setPosition( (App.getSize().x/5-vitesse2.getLocalBounds().width/2)*2, (App.getSize().y/4)+4*taille_Font);

        vitesse3.setFont(Font);
        vitesse3.setCharacterSize(taille_Font);
        vitesse3.setString("x3");
        vitesse3.setPosition( (App.getSize().x/5-vitesse3.getLocalBounds().width/2)*3, (App.getSize().y/4)+4*taille_Font);

        vitesse4.setFont(Font);
        vitesse4.setCharacterSize(taille_Font);
        vitesse4.setString("x4");
        vitesse4.setPosition( (App.getSize().x/5-vitesse4.getLocalBounds().width/2)*4, (App.getSize().y/4)+4*taille_Font);

        vitesse5.setFont(Font);
        vitesse5.setCharacterSize(taille_Font);
        vitesse5.setString("x5");
        vitesse5.setPosition( (App.getSize().x/5-vitesse5.getLocalBounds().width/2)*5, (App.getSize().y/4)+4*taille_Font);

        Sprite viseur = loadViseur(App);

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

                    if (event.type == Event.Type.MOUSE_BUTTON_PRESSED) {

                        event.asMouseEvent();
                        pos = Mouse.getPosition(App);

                        if(Menu1.getGlobalBounds().contains((float)pos.x, (float)pos.y)){
                            menu = 0;
                            setMusicPlayerChoice(1);
                            System.out.println("menu1  "+ getMusicPlayerChoice());

                        }
                        else if(Menu2.getGlobalBounds().contains((float)pos.x, (float)pos.y)){
                            menu = 1;
                            setMusicPlayerChoice(2);
                            System.out.println("menu2 "+ getMusicPlayerChoice());

                        }
                        else if(retour.getGlobalBounds().contains((float)pos.x, (float)pos.y)){
                            menu = 2;
                            sound.stop();
                            // System.out.println("menu0");
                        }
                        else if(start.getGlobalBounds().contains((float)pos.x, (float)pos.y)) {
                            menu = 3;
                            sound.stop();
                        }
                    }

                    //Key pressed
                    else if (event.type == Event.Type.KEY_PRESSED)
                    {
                        event.asKeyEvent();
                        sound.stop();

                        if (Keyboard.isKeyPressed(Keyboard.Key.ESCAPE))
                            return 1;

                        if (Keyboard.isKeyPressed(Keyboard.Key.DOWN)){
                            menu++;
                            if(menu>3)
                                menu = 0;
                        }

                        if (Keyboard.isKeyPressed(Keyboard.Key.UP)) {
                            menu--;
                            if(menu<0)
                                menu = 3;
                        }


                        if (Keyboard.isKeyPressed(Keyboard.Key.RETURN)) {
                            sound.stop();
                            startMusic("rsc/sound/son_poules_menus.wav");
                            if (menu == 0) {
                                setMusicPlayerChoice(1);
                                return (3);
                            } else if (menu == 1) {
                                //Let's get work...
                                setMusicPlayerChoice(2);
                                return (3);}
                            else if (menu == 2){

                                return 1;
                            }
                            else if (menu == 3){
                                return 3;
                            }
                        }
                    }

                    if (event.type == Event.Type.MOUSE_BUTTON_PRESSED) {
                        event.asMouseEvent();
                        pos = Mouse.getPosition(App);
                        startMusic("rsc/sound/son_poules_menus.wav");

                        if(vitesse1.getGlobalBounds().contains((float)pos.x, (float)pos.y)){
                            vitesse = 0;
                            //System.out.println("menu1");
                        }
                        else if(vitesse2.getGlobalBounds().contains((float)pos.x, (float)pos.y)){
                            vitesse = 1;
                            // System.out.println("menu0");
                        }
                        else if(vitesse3.getGlobalBounds().contains((float)pos.x, (float)pos.y)){
                            vitesse = 2;
                            // System.out.println("menu0");
                        }
                        else if(vitesse4.getGlobalBounds().contains((float)pos.x, (float)pos.y)){
                            vitesse = 3;
                            // System.out.println("menu0");
                        }
                        else if(vitesse5.getGlobalBounds().contains((float)pos.x, (float)pos.y)){
                            vitesse = 4;
                            // System.out.println("menu0");
                        }
                        speedPlayerChoice = vitesse + 1;
                        //musicPlayerChoice = menu;
                    }

                    if (event.type == Event.Type.MOUSE_MOVED) {
                        event.asMouseEvent();
                        pos = Mouse.getPosition(App);

                        if(Menu1.getGlobalBounds().contains((float)pos.x, (float)pos.y)){
                            select = 0;
                            //System.out.println("menu1");

                        }
                        else if(Menu2.getGlobalBounds().contains((float)pos.x, (float)pos.y)){
                            select = 1;
                            // System.out.println("menu0");
                        }
                        else if(retour.getGlobalBounds().contains((float)pos.x, (float)pos.y)){
                            select = 2;
                            // System.out.println("menu0");
                        }
                        else if(start.getGlobalBounds().contains((float)pos.x, (float)pos.y)) {
                            select = 3;
                        }
                        else if(vitesse1.getGlobalBounds().contains((float)pos.x, (float)pos.y)) {
                            select = 4;
                        }
                        else if(vitesse2.getGlobalBounds().contains((float)pos.x, (float)pos.y)) {
                            select = 5;
                        }
                        else if(vitesse3.getGlobalBounds().contains((float)pos.x, (float)pos.y)) {
                            select = 6;
                        }
                        else if(vitesse4.getGlobalBounds().contains((float)pos.x, (float)pos.y)) {
                            select = 7;
                        }
                        else if(vitesse5.getGlobalBounds().contains((float)pos.x, (float)pos.y)) {
                            select = 8;
                        }
                    }
                }
            }

            if (menu == 0)
            {
                Menu1.setColor(Color.RED);
                Menu2.setColor(Color.WHITE);
                start.setColor(Color.WHITE);
                retour.setColor(Color.WHITE);
            }
            else if (menu == 1)
            {
                Menu1.setColor(Color.WHITE);
                Menu2.setColor(Color.RED);
                start.setColor(Color.WHITE);
                retour.setColor(Color.WHITE);
            }
            else if (menu == 2)
            {
                Menu1.setColor(Color.WHITE);
                Menu2.setColor(Color.WHITE);
                start.setColor(Color.WHITE);
                retour.setColor(Color.RED);
                sound.stop();
                return 1;
            }
            else if (menu == 3) {
                Menu1.setColor(Color.WHITE);
                Menu2.setColor(Color.WHITE);
                start.setColor(Color.RED);
                retour.setColor(Color.WHITE);
                sound.stop();
                return 3;
            }


            if (vitesse == 0)
            {
                vitesse1.setColor(Color.RED);
                vitesse2.setColor(Color.WHITE);
                vitesse3.setColor(Color.WHITE);
                vitesse4.setColor(Color.WHITE);
                vitesse5.setColor(Color.WHITE);
            }
            else if (vitesse == 1)
            {
                vitesse1.setColor(Color.WHITE);
                vitesse2.setColor(Color.RED);
                vitesse3.setColor(Color.WHITE);
                vitesse4.setColor(Color.WHITE);
                vitesse5.setColor(Color.WHITE);
            }
            else if (vitesse == 2)
            {
                vitesse1.setColor(Color.WHITE);
                vitesse2.setColor(Color.WHITE);
                vitesse3.setColor(Color.RED);
                vitesse4.setColor(Color.WHITE);
                vitesse5.setColor(Color.WHITE);
            }
            else if (vitesse == 3)
            {
                vitesse1.setColor(Color.WHITE);
                vitesse2.setColor(Color.WHITE);
                vitesse3.setColor(Color.WHITE);
                vitesse4.setColor(Color.RED);
                vitesse5.setColor(Color.WHITE);
            }
            else if (vitesse == 4)
            {
                vitesse1.setColor(Color.WHITE);
                vitesse2.setColor(Color.WHITE);
                vitesse3.setColor(Color.WHITE);
                vitesse4.setColor(Color.WHITE);
                vitesse5.setColor(Color.RED);
            }

            switch (select) {
                case 0:
                    Menu1.setColor(Color.RED);
                    break;
                case 1:
                    Menu2.setColor(Color.RED);
                    break;
                case 2:
                    retour.setColor(Color.RED);
                    break;
                case 3:
                    start.setColor(Color.RED);
                    break;
                case 4:
                    vitesse1.setColor(Color.RED);
                    break;
                case 5:
                    vitesse2.setColor(Color.RED);
                    break;
                case 6:
                    vitesse3.setColor(Color.RED);
                    break;
                case 7:
                    vitesse4.setColor(Color.RED);
                    break;
                case 8:
                    vitesse5.setColor(Color.RED);
                    break;
            }


            App.clear();
            Vector2f posViseur= new Vector2f((float)pos.x, (float)pos.y);
            viseur.setPosition(posViseur);

            App.draw(Titre);
            App.draw(retour);
            App.draw(start);
            App.draw(Menu1);
            App.draw(Menu2);
            App.draw(vitesseLabel);
            App.draw(vitesse1);
            App.draw(vitesse2);
            App.draw(vitesse3);
            App.draw(vitesse4);
            App.draw(vitesse5);
            App.draw(viseur);
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

    public static int getSpeedPlayerChoice() {
        return speedPlayerChoice;
    }

    public static void setSpeedPlayerChoice(int speedPlayerChoice) {
        SelectMusic.speedPlayerChoice = speedPlayerChoice;
    }

    public static int getMusicPlayerChoice() {
        return musicPlayerChoice;
    }

    public static void setMusicPlayerChoice(int musicPlayerChoice) {
        SelectMusic.musicPlayerChoice = musicPlayerChoice;
    }



}
