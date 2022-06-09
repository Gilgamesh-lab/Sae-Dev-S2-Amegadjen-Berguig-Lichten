package application.modele.outils;

import application.modele.Environnement;
import application.modele.ressources.Ressource;
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
	 * @return 
	 */
	public Ressource utiliser(int lieu) {
		if (super.getEnvironnement().getCarte().emplacement(lieu) instanceof Terre)
			return super.getEnvironnement().getCarte().attaquerBloc(lieu, DEGATS);
		return null;
	}
}
