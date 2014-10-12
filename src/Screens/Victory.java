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
public class Victory extends cScreen {

    public int Run(RenderWindow App) {
        boolean Running = true;

        Text VictoryText = new Text();
        Font Font = new Font();
        try {
            Font.loadFromFile(Paths.get("rsc/font/Frank Knows.ttf"));
        } catch (IOException e) {
            e.printStackTrace();
            return (-1);
        }
        VictoryText.setFont(Font);
        VictoryText.setCharacterSize(50);
        VictoryText.setString("Vous avez vaincu tous vos ennemis !");
        VictoryText.setPosition(App.getSize().x / 2 - VictoryText.getLocalBounds().width / 2, App.getSize().y / 2 - VictoryText.getLocalBounds().height / 2);

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


            App.draw(VictoryText);
            App.display();

            //Clearing screen
            App.clear();
        }

        //Never reaching this point normally, but just in case, exit the application
        System.out.println("game over finit");
        return (6);
    }
}
