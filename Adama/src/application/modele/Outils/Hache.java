package application.modele.Outils;

import application.modele.Environnement;
import application.modele.Ressources.Bois;

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
	 */
	public void utiliser(int lieu) {
		int largeur=super.getEnvironnement().getCarte().LARGEUR;
		if (super.getEnvironnement().getCarte().emplacement(lieu) instanceof Bois)
			super.getEnvironnement().getCarte().attaquerBloc(lieu, DEGATS);
		try {
			if (super.getEnvironnement().getCarte().emplacement(lieu-largeur) instanceof Bois)
				this.utiliser(lieu-largeur);
			//		if (super.getEnvironnement().getCarte().emplacement(lieu+largeur) instanceof Bois)
			//			this.utiliser(lieu+largeur);
		} catch(IndexOutOfBoundsException e) {
			System.err.println("Pas du bois");
		}
	}


}
