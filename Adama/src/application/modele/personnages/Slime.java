package application.modele.personnages;

import application.modele.Environnement;

public class Slime extends Ennemis {
	

	public Slime(int pv, int x, int y, int vitesseDeplacement,Environnement environnement,int[] taille, int degat, boolean vole)  {
		super(pv, x, y, vitesseDeplacement,environnement,taille, degat, vole);
	}
	
	public Slime(Environnement environnement,int[] taille, int degat, boolean vole)  {
		super(environnement,taille, degat, vole);
	}

}
