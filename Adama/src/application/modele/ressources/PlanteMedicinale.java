package application.modele.ressources;

import application.modele.exception.ErreurInventairePlein;
import application.modele.personnages.Joueur;

public class PlanteMedicinale extends Plante {

	public PlanteMedicinale(int x, int y, int indice) {
		super(x, y, indice);
	}

	public PlanteMedicinale(int indice) {
		super(indice);
	}

	@Override
	public void utiliser(int val, Joueur joueur) throws ErreurInventairePlein {
		// TODO Auto-generated method stub
		
	}

}
