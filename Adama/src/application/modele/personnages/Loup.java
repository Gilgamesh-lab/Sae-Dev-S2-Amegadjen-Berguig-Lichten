package application.modele.personnages;

import application.modele.Environnement;

public class Loup extends Ennemis {

	public Loup(int pv, int x, int y, int vitesseDeplacement,Environnement environnement,int[] taille, int degat, boolean vole)  {
		super(pv, x, y, vitesseDeplacement,environnement,taille, degat, vole);
	}
	
	public Loup(Environnement environnement,int[] taille, int degat, boolean vole)  {
		super(environnement,taille, degat, vole);
	}

}
