package Screens;

import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundBuffer;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Text;
import org.jsfml.window.Keyboard;
import org.jsfml.window.event.Event;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Package Screens
 * Created by steven on 10/12/14.
 */
public class ScoreScreen extends cScreen {

    public int Run(RenderWindow App) {
        boolean Running = true;
        Text numberChickenKillLabel = new Text();
        Text scoreLabel = new Text();
        Text numberChickenKill = new Text();
        Text score = new Text();
        Font font = new Font();

        try {
            font.loadFromFile(Paths.get("rsc/font/Frank Knows.ttf"));
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
        numberChickenKillLabel.setFont(font);
        numberChickenKillLabel.setCharacterSize(50);
        numberChickenKillLabel.setString("Number of chicken kill: ");
        numberChickenKillLabel.setPosition(App.getSize().x / 2 - numberChickenKillLabel.getLocalBounds().width / 2, App.getSize().y / 4);

        numberChickenKill.setFont(font);
        numberChickenKill.setCharacterSize(50);
        numberChickenKill.setString("10000");
        numberChickenKill.setPosition(App.getSize().x / 2 - numberChickenKillLabel.getLocalBounds().width / 2, (App.getSize().y / 4) + 50);

        scoreLabel.setFont(font);
        scoreLabel.setCharacterSize(50);
        scoreLabel.setString("Score: ");
        scoreLabel.setPosition( App.getSize().x/2-scoreLabel.getLocalBounds().width/2, (App.getSize().y/4)*2);

        score.setFont(font);
        score.setCharacterSize(50);
        score.setString("1000000");
        score.setPosition(App.getSize().x/2-scoreLabel.getLocalBounds().width/2, (App.getSize().y/4)*2);

        long debut_bootsplash = System.currentTimeMillis();
        int duree = 4000;

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
            App.draw(scoreLabel);
            App.draw(numberChickenKillLabel);
            App.display();

            //Clearing screen
            App.clear();
        }

        //Never reaching this point normally, but just in case, exit the application
        System.out.println("game over finit");
        return (1);
    }
}
