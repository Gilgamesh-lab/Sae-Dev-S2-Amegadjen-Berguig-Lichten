package application.modele.armes;

import application.modele.exception.ErreurInventairePlein;
import application.modele.personnages.Joueur;

public class Poing extends Arme {

	public Poing() {
		super(1, 1, 2);
	}

	@Override
	public void utiliser(int val, Joueur joueur) throws ErreurInventairePlein {
		// TODO Auto-generated method stub
		
	}


}
