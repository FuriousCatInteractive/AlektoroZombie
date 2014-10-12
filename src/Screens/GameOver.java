package Screens;

import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Text;
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


            App.draw(gameOver);
            App.display();

            //Clearing screen
            App.clear();
        }

        //Never reaching this point normally, but just in case, exit the application
        System.out.println("game over finit");
        return (1);
    }
}
