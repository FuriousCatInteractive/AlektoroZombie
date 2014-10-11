package World;


public final class GridLevel {

	/**************************************Attributs*******************************************/
	private static volatile GridLevel instance = null;

	final private int nbCaseW = 10;//nombre de cases en largeur
	final private int nbCaseH = 10;//nombre de case en hauteur
	private Case[][] grilleCases;

	/**************************************Methodes********************************************/

	//Constructeur de l'objet, prive afin d'implementer un singleton
	private GridLevel() {

		grilleCases = new Case[nbCaseW][nbCaseH];
		int constrX = 0, constrY, cpti, cptj;

		for(cpti = 0; cpti < nbCaseW;cpti++)
		{
			constrY=0;
			for(cptj = 0; cptj < nbCaseH; cptj++)
			{
				this.grilleCases[cpti][cptj] = new Case(constrX, constrY);
				constrY += this.grilleCases[cpti][cptj].getHeight();
			}
			constrX += this.grilleCases[cpti][--cptj].getwidth();
		}
	}
	

	//Methode permettant de renvoyer une instance du singleton
	public final static GridLevel getInstance(){
		//Le "Double-Checked Singleton" permet 
		//d'éviter un appel coûteux à synchronized, 
		//une fois que l'instanciation est faite.
		if(GridLevel.instance == null){
			GridLevel.instance = new GridLevel();
		}
		return GridLevel.instance;

	}


}
