package application.modele.Outils;

import application.modele.Environnement;
import application.modele.Ressources.Terre;

public class Pelle extends Outil {

	private final static int DEGATS=10;
//	private int x;
//	private int y;
	
	public Pelle(Environnement env) {
		super(env, DEGATS);
	}

	public void utiliser(int lieu) {
		if (super.getEnvironnement().getCarte().emplacement(lieu) instanceof Terre)
			super.getEnvironnement().getCarte().attaquerBlock(lieu, DEGATS);
	}

}
