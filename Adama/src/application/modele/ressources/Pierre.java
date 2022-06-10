
package application.modele.ressources;

import application.modele.exception.ErreurInventairePlein;
import application.modele.personnages.Joueur;

public class Pierre extends Ressource {

	

	public Pierre(boolean posable, int x, int y, int indice) {
		super(posable, x, y, indice);
		
	}
	
	
	public Pierre(int indice) {
		super(false, 5,5, indice);
	}

	@Override
	public void utiliser(int val, Joueur joueur) throws ErreurInventairePlein {
		// TODO Auto-generated method stub
		
	}

}