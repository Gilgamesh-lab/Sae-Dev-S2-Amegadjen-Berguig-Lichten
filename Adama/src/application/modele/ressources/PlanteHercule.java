package application.modele.ressources;

import application.modele.exception.ErreurInventairePlein;
import application.modele.personnages.Joueur;

public class PlanteHercule extends Plante {

	public PlanteHercule(int x, int y, int indice) {
		super(x, y, indice);
		
	}

	public PlanteHercule(int indice) {
		super(indice);
		
	}

	@Override
	public void utiliser(int val, Joueur joueur) throws ErreurInventairePlein {
		// TODO Auto-generated method stub
		
	}

}
