package Screens;

import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundBuffer;
import org.jsfml.graphics.*;
import org.jsfml.window.Keyboard;
import org.jsfml.window.event.Event;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by coco on 14-10-12.
 */
public class GameOver extends cScreen {

    public int Run(RenderWindow App) {
        boolean Running = true;

        Text gameOver = new Text();
        Font Font = new Font();
        try {
            Font.loadFromFile(Paths.get("rsc/font/Frank Knows.ttf"));
        } catch (IOException e) {
            e.printStackTrace();
            return (-1);
        }
        gameOver.setFont(Font);
        gameOver.setCharacterSize(50);
        gameOver.setString("GAME OVER ");
        gameOver.setPosition(App.getSize().x / 2 - gameOver.getLocalBounds().width / 2, App.getSize().y / 2 - gameOver.getLocalBounds().height / 2);

        Sprite dramatic = new Sprite();
        Texture maTexture = new Texture(); // déclaration d'une texture

        try {
            maTexture.loadFromFile(Paths.get("rsc/img/dramatic.jpeg")); // on charge la texture qui se trouve dans notre dossier assets
        } catch (IOException e) {
            e.printStackTrace();
        }
        dramatic.setTexture(maTexture);




        int duree = 7000;



        //Clearing screen
        App.clear();
        App.display();
        long current_time;
        try {
            Thread.sleep(800);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        long debut_bootsplash = System.currentTimeMillis();
        startMusic("rsc/sound/dramatic_chipmunks.wav");

        while (System.currentTimeMillis() - debut_bootsplash < duree && Running) {
            //Verifying events
            for (Event event : App.pollEvents()) {
                {
                    // Window closed
                    if (event.type == event.type.CLOSED) {
                        return (-1);
                    }
                    //Key pressed
                    if (event.type == Event.Type.KEY_PRESSED) {
                        event.asKeyEvent();

                        if (Keyboard.isKeyPressed(Keyboard.Key.ESCAPE))
                            return (-1);
                        else {

                            return 1;
                        }
                    }
                }
            }

            App.draw(gameOver);
             current_time = System.currentTimeMillis();

            if(current_time -debut_bootsplash < 1100)
            {
                dramatic.setOrigin(dramatic.getGlobalBounds().width/2,dramatic.getGlobalBounds().height/2 );
                dramatic.setPosition(App.getSize().x/2,App.getSize().y/2);
                App.draw(dramatic);
            }
            else if(current_time -debut_bootsplash >= 1100 && current_time -debut_bootsplash <= 1600)
            {
                dramatic.setOrigin(dramatic.getGlobalBounds().width/6,dramatic.getGlobalBounds().height/2  );
                dramatic.setPosition(App.getSize().x/2,(int)(11*App.getSize().y/10));
                dramatic.setScale(2.4f,2.4f);
                App.draw(dramatic);
            }
            else if(current_time -debut_bootsplash > 1600 && current_time -debut_bootsplash <= 3700)
            {
                dramatic.setOrigin(dramatic.getGlobalBounds().width/12,dramatic.getGlobalBounds().height/2  );
                dramatic.setPosition(App.getSize().x/2,25*App.getSize().y/10);
                dramatic.setScale(3.8f,3.8f);
                App.draw(dramatic);
            }

            App.display();

            //Clearing screen
            App.clear();
        }

        //Never reaching this point normally, but just in case, exit the application
        System.out.println("game over finit");
        return (6);
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
