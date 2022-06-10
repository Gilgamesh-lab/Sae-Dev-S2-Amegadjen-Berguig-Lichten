package application.modele.outils;

import application.modele.Carte;
import application.modele.Environnement;
import application.modele.exception.ErreurInventairePlein;
import application.modele.personnages.Joueur;
import application.modele.ressources.Bois;
import application.modele.ressources.Ressource;

public class Hache extends Outil {

	private final static int DEGATS=10;
	//	private int x;
	//	private int y;

	/**
	 * Créer un outil hache
	 * @param env l'environnement dans lequel elle se trouve
	 */
	public Hache(Environnement env) {
		super(env, DEGATS);
	}

	/**
	 * Permet d'utiliser la hache elle fait des dégat au bloc de Bois viser et ceux au-dessus
	 * @param lieu l'indice où sont donné les coups de hache
	 * @return 
	 * @throws ErreurInventairePlein 
	 */
	public void utiliser(int lieu, Joueur joueur) throws ErreurInventairePlein {
		int largeur=Carte.LARGEUR;
		try {
			if (super.getEnvironnement().getCarte().emplacement(lieu-largeur) instanceof Bois)
				this.utiliser(lieu-largeur, joueur);
		} catch(IndexOutOfBoundsException e) {
			System.err.println("Pas du bois");
		}//TODO faire en sorte que l'on récupère plus que un bois si récursivité arrive
		if (super.getEnvironnement().getCarte().emplacement(lieu) instanceof Bois)
			joueur.getInventaire().ajouter(super.getEnvironnement().getCarte().attaquerBloc(lieu, DEGATS));
	}
}
