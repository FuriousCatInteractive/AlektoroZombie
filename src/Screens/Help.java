package Screens;

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
public class Help extends  cScreen {
    public int Run(RenderWindow App){

        boolean Running = true;
        Text scenario = new Text();
        Font Font = new Font();
        try {
            Font.loadFromFile(Paths.get("rsc/font/mrsmonsterrotal.ttf"));
        } catch (IOException e) {
            e.printStackTrace();
            return (-1);
        }

        int taille_Font = 27;

        scenario.setFont(Font);
        scenario.setCharacterSize(taille_Font);
        scenario.setString("Bienvenue dans AlektoroZombie !\n" +
                "1.Vous pouvez faire pivoter Robert grâce à\n" +
                " votre souris.\n" +
                "2.Pour lancer des cailloux à nos ennemis jurés \n" +
                " appuyez sur le bouton gauche de votre souris.\n" +
                "3.Plus les poulets sont proches de la ligne\n" +
                " verte plus vous gagnez de points.\n" +
                "4.On gagne le maximum de point quand\n" +
                " les poulets deviennent verts.\n" +
                "5.Attention Robert ne possède que 3 vies.\n" +
                "6.Ecoutez bien la musique, elle vous aidera\n" +
                " à mieux appréhender les vagues de poulets !\n" +
                "7.Bonne chance pour votre guerre\n" +
                " contre ces saletés à plume.\n\n" +
                "Appuyez sur une touche pour retouner à l'écran des titres.");
        scenario.setPosition( App.getSize().x/2-scenario.getLocalBounds().width/2, 100);



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
                    if(event.type == Event.Type.MOUSE_BUTTON_PRESSED) {
                        return 1;
                    }
                    //Key pressed
                    if (event.type == Event.Type.KEY_PRESSED)
                    {
                        event.asKeyEvent();

                        if (Keyboard.isKeyPressed(Keyboard.Key.ESCAPE))
                            return (-1);
                        else
                        {

                            return 1;
                        }
                    }
                }
            }

            App.draw(scenario);
            App.display();

            //Clearing screen
            App.clear();
        }

        //Never reaching this point normally, but just in case, exit the application
        System.out.println("bootsplash finit");
        return (1);
    }
}
