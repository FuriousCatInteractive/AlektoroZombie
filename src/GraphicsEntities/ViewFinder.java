package GraphicsEntities;

import MoveBehavior.MathUtils;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

/**
 * Created by steven on 10/11/14.
 */
public class ViewFinder implements Drawable{
    private Vector2i posMouse;
    private RectangleShape viewFinder;

    public ViewFinder() {
        posMouse = new Vector2i(0, 0);
        viewFinder = new RectangleShape(new Vector2f(150, 5));
        viewFinder.setFillColor(Color.RED);
    }

    public void updateViewFinder(Vector2i posMouse, Vector2f playerPos) {
        this.viewFinder.setPosition(playerPos);
        this.posMouse = posMouse;
        Vector2f tmpPosMouse = new Vector2f(posMouse.x, posMouse.y);
        Vector2f vectorMouse = Vector2f.sub(tmpPosMouse, playerPos);
        double angle = 2 * Math.atan(vectorMouse.y/(vectorMouse.x + Math.sqrt(Math.pow(vectorMouse.x, 2) + Math.pow(vectorMouse.y, 2))));
        viewFinder.rotate((float)(angle * (180 / Math.PI)));
    }

    @Override
    public void draw(RenderTarget renderTarget, RenderStates renderStates) {
        renderTarget.draw(viewFinder, renderStates);
    }
}
