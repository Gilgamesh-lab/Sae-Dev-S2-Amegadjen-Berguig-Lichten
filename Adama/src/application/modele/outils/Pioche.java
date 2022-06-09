package application.modele.outils;

import application.modele.Environnement;
import application.modele.ressources.Pierre;
import application.modele.ressources.Ressource;

public class Pioche extends Outil {

	private final static int DEGATS=10;
//	private int x;
//	private int y;
	
	/**
	 * Créer un outil pioche
	 * @param env l'environnement dans lequel elle se trouve
	 */
	public Pioche(Environnement env) {
		super(env, DEGATS);
	}

	/**
	 * Permet d'utiliser la pioche sur le bloc visé
	 * @param lieu indice du bloc visé
	 * @return 
	 */
	public Ressource utiliser(int lieu) {
		if (super.getEnvironnement().getCarte().emplacement(lieu) instanceof Pierre)
			return super.getEnvironnement().getCarte().attaquerBloc(lieu, DEGATS);
		return null;
	}
}
