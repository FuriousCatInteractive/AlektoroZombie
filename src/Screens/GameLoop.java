package Screens;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.Keyboard;
import org.jsfml.window.event.Event;

/**
 * Created by coco on 14-10-11.
 */
public class GameLoop extends cScreen {

    public int Run(RenderWindow App) {

        boolean Running = true;

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
                            return -1;

                        }
                    }
                }
            }
        return -1;//exit
    }
}
