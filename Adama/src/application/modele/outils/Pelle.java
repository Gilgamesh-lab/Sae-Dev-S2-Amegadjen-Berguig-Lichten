package application.modele.outils;

import application.modele.Environnement;
import application.modele.exception.ErreurInventairePlein;
import application.modele.ressources.Terre;

public class Pelle extends Outil {

	private final static int DEGATS=10;
//	private int x;
//	private int y;
	
	/**
	 * Créer un outil pelle
	 * @param env l'environnement dans lequel elle se trouve
	 */
	public Pelle(Environnement env) {
		super(env, DEGATS);
	}
	
	/**
	 * Permet d'utiliser la pelle sur le bloc visé
	 * @param lieu indice du bloc visé
	 * @throws ErreurInventairePlein 
	 */
	public void pelleter(int lieu) throws ErreurInventairePlein {
		if (super.getEnvironnement().getCarte().emplacement(lieu) instanceof Terre)
			super.getEnvironnement().getCarte().attaquerBloc(lieu, DEGATS);
	}
	
	public void utiliser(int val) {
		
	}

}
