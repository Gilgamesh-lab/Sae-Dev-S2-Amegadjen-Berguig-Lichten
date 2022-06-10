package application.modele.ressources;

import application.modele.exception.ErreurInventairePlein;
import application.modele.personnages.Joueur;

public class Eau extends Ressource {

	public Eau() {
		super(false, -1, -1, -1);
	}

	@Override
	public void utiliser(int val, Joueur joueur) throws ErreurInventairePlein {
		// TODO Auto-generated method stub
		
	}

}
