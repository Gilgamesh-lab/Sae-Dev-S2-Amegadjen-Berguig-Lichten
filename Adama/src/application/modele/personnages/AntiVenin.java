package application.modele.personnages;

import application.modele.exception.ErreurInventairePlein;
import application.modele.ressources.Ressource;

public class AntiVenin extends Ressource {

	public AntiVenin(int indice) {
		super(false, -1, -1, indice);
	}

	@Override
	public void utiliser(int val, Joueur joueur) throws ErreurInventairePlein {
		// TODO Auto-generated method stub
		
	}
}
