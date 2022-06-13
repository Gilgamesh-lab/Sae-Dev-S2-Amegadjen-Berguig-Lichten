package application.modele.personnages;

import application.modele.Environnement;
import application.modele.exception.ErreurObjetIntrouvable;

public class Geant extends Ennemis {

	public Geant(int pv, int x, int y, int vitesseDeplacement,Environnement environnement,int[] taille, int degat, boolean vole)  {
		super(pv, x, y, vitesseDeplacement,environnement,taille, degat, vole);
	}

	public Geant(Environnement environnement,int[] taille, int degat, boolean vole)  {
		super(environnement,taille, degat, vole);
	}



	@Override
	public void agir() {

	}
}
