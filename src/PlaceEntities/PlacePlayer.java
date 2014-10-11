package PlaceEntities;

import org.jsfml.system.Time;
import org.jsfml.window.Window;

import Entities.GameBaseEntity;


public class PlacePlayer {
	
	
	
	public PlacePlayer(){
		
	}
	
	protected void placement(GameBaseEntity player, Window window1)
	{
		player.setOrigin(player.getLocalBounds().width/2, player.getLocalBounds().height/2);
        player.setPosition(window1.getSize().x/2,window1.getSize().y/2);
	}
}