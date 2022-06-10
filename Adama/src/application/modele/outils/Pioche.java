package application.modele.outils;

import application.modele.Carte;
import application.modele.Environnement;
import application.modele.exception.ErreurInventairePlein;
import application.modele.personnages.Joueur;
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
	 * @throws ErreurInventairePlein 
	 */
	public void utiliser(int lieu, Joueur joueur) throws ErreurInventairePlein {
		Carte carte = super.getEnvironnement().getCarte();
		if (carte.emplacement(lieu) instanceof Pierre)
			joueur.getInventaire().ajouter(carte.attaquerBloc(lieu, DEGATS));
	}
}
