import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundBuffer;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Time;
import org.jsfml.window.Keyboard;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

import java.io.IOException;
import java.nio.file.Paths;

import static org.jsfml.graphics.Color.BLACK;


public class BootSplash extends Sprite{

    private Texture maTexture;
    private float ratio;
    private RenderWindow mafenetre;
    private float alpha;
    private boolean ok;
    private boolean ok2;

    public BootSplash(RenderWindow fenetre){

        mafenetre = fenetre;
        maTexture = new Texture(); // déclaration d'une texture

        try {
            maTexture.loadFromFile(Paths.get("rsc/img/logo-furious-cat-interactive.png")); // on charge la texture qui se trouve dans notre dossier assets
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setTexture(maTexture); // on applique la texture à notre sprite
        ratio = (float)mafenetre.getSize().y/(maTexture.getSize().y*2);
        this.setScale(ratio, ratio);


        float textureX = maTexture.getSize().x*ratio/2;
        float textureY = maTexture.getSize().y*ratio/2;

        float posX = mafenetre.getSize().x/2;
        float posY = mafenetre.getSize().y/2;

        //System.out.println(posX +" "+ posY);


        setOrigin(maTexture.getSize().x/2,maTexture.getSize().y/2);
        setPosition(posX, posY);

        System.out.println("origin= " + this.getOrigin().x+" "+ this.getOrigin().y);
        System.out.println("position = " + this.getPosition().x +" "+getPosition().y);

        alpha=0;
        ok = false;
        ok2=false;

    }

    public void update()
    {
        // long début_temps= System.currentTimeMillis();

        //Boucle principale qui s’exécute tant que la fenêtre est ouverte
        //while (System.currentTimeMillis()-début_temps<time*1000) {

       // mafenetre.clear(BLACK); // On remplit la fenêtre avec la couleur noire

        if(alpha<=255 && !ok)
            alpha+=2f;
        else if(alpha>=0)
        {
            ok = true;
            alpha-=2f;
        }
        this.setColor(new Color(255, 255, 255, (int)alpha)); // half transparent

        if(getRotation()<360)
        {
            this.rotate(3f);
            ok2 = true;
        }
        else if(ok2==true)
        {
            this.startMusic("res/Meow.ogg");
            ok2=false;
        }
    }

    private void setCenterRotation(){

    }

    private void centerSprite(){
        float posX = maTexture.getSize().x*ratio/2;
        float posY = maTexture.getSize().y*ratio/2;
        setPosition(posX ,posY);

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
