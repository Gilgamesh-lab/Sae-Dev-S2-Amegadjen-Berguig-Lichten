package application.modele.ressources;

import application.modele.exception.ErreurInventairePlein;
import application.modele.personnages.Joueur;

public class PlanteDeNike extends Plante {

	
	public PlanteDeNike(int x, int y, int indice) {
		super(x, y, indice);
	}

	public PlanteDeNike(int indice) {
		super(indice);
	}

	@Override
	public void utiliser(int val, Joueur joueur) throws ErreurInventairePlein {
		// TODO Auto-generated method stub
		
	}

}
