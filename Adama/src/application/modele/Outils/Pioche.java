package application.modele.Outils;

import application.modele.Environnement;
import application.modele.Ressources.Pierre;

public class Pioche extends Outil {

	private final static int DEGATS=10;
//	private int x;
//	private int y;
	
	public Pioche(Environnement env) {
		super(env, DEGATS);
	}

	public void utiliser(int lieu) {
		if (super.getEnvironnement().getCarte().emplacement(lieu) instanceof Pierre)
			super.getEnvironnement().getCarte().attaquerBlock(lieu, DEGATS);
	}

}
