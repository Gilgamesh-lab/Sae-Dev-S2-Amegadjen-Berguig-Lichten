package application.modele.personnages;

import application.modele.Environnement;
import application.modele.exception.ErreurObjetIntrouvable;

public class Loup extends Ennemis {

	public Loup(int pv, int x, int y, int vitesseDeplacement,Environnement environnement,int[] taille, int degat)  {
		super(pv, x, y, vitesseDeplacement,environnement,taille, degat);
	}

	public Loup(Environnement environnement,int[] taille, int degat)  {
		super(environnement,taille, degat);
	}
	
	@Override
	public void agir() {

	}

}
