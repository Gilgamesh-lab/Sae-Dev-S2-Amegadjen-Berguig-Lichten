package application.modele.personnages;

import application.modele.exception.ErreurInventairePlein;
import application.modele.ressources.Ressource;

public class Fils extends Ressource {

	public Fils(int indice) {
		super(false, 0, -1, -1, -1, indice);
	}

	@Override
	public void utiliser(int val, Joueur joueur) throws ErreurInventairePlein {
		// TODO Auto-generated method stub
		
	}
}
