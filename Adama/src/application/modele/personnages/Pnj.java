package application.modele.personnages;

import application.modele.Environnement;
import application.modele.exception.ErreurObjetIntrouvable;

public abstract class Pnj extends Personnage {

	public Pnj(int pv, int x, int y, int vitesseDeplacement, Environnement environnement, int[] taille) {


		super(10, x, y, 10, environnement, taille);

	}
	
	public abstract void agir();

}
