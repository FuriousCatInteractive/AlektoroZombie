
import Screens.*;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderWindow;

import org.jsfml.system.Vector2f;

import org.jsfml.window.VideoMode;


public class Main {

    public static void main(String[] args) {

        int WINDOW_H = 700;
        int WINDOW_W = 700;

        RenderWindow window1 = new RenderWindow();
        window1.create(new VideoMode(WINDOW_W, WINDOW_H), "fenetre JSFML");//-1 = fullscreen
        window1.setFramerateLimit(60);
        window1.setKeyRepeatEnabled(true);
        //window1.setMouseCursorVisible(false);

        FloatRect rectWindow1 = new FloatRect(new Vector2f(15, 15), new Vector2f(WINDOW_W - 30, WINDOW_H - 30));
        System.out.println("top: " + rectWindow1.top + " left: " + rectWindow1.left + " width: " + rectWindow1.height);

        //Applications variables
        cScreen[] Screens = new cScreen[5];
        int screen = 0;
        Screens[0] = new BootSplash();
        Screens[1] = new Menu();
        Screens[2] = new SelectMusic();
        Screens[3] = new GameLoop();
        Screens[4] = new GameOver();

        while (screen >= 0) {
            screen = Screens[screen].Run(window1);
        }

    }
}