package World;
import java.util.*;

public class Case {

	/**************************************Attributs*******************************************/
	final private int width = 20;
	final private int height = 20;
	final private int oriX;
	final private int oriY;
	private List listEntity;
	
	/**************************************Methodes********************************************/
	
	public Case(int X, int Y)
	{
		oriX = X;
		oriY = Y;
		listEntity = new ArrayList();
	}
	
	public int getOriX()
	{
		return oriX;
	}
	
	public int getOriY()
	{
		return oriY;
	}
	
	public int getwidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height; 
	}
}
  