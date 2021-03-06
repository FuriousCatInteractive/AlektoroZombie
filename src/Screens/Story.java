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
public class Story extends  cScreen {

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
        scenario.setString("Quand on parle de zombies on pense souvent\n" +
                " à d'effroyables prédateurs en quête de matière \n" +
                "grise à ingurgiter. En réalité ces pauvres zombies\n" +
                " sont en fait les proies d'un nouveau grand \n" +
                "prédateur: les POULETS. En effet la maladie qui \n" +
                "touche le monde entier depuis quelques années ne \n" +
                "contamine que les êtres vivants doués d'un minimum\n " +
                "d'intelligence, ce qui n'est pas le cas de ces\n " +
                "gallinacés. Ces volatiles sont donc logiquement\n " +
                "devenus l'espèce majoritaire sur la planète et \n" +
                "cherchent à éliminer toute trace de notre \n" +
                "ancienne civilisation en commencant par les \n" +
                "individus les plus lents: les zombies. C'est \n" +
                "anisi que ces morts-vivants sont devenus sujets \n" +
                "à l'alektorophobie (la phobie des poulets). \n" +
                "Aidez-donc notre ami Robert à se débarasser \n" +
                "de ces créatures malfaisantes et sournoises,\n" +
                " le tout en musique!!\n");
        scenario.setPosition( App.getSize().x/2-scenario.getLocalBounds().width/2, 100);


        long debut_bootsplash = System.currentTimeMillis();
        int duree=45000;

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
