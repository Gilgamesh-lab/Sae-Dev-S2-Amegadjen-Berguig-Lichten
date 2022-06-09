package application.modele.personnages;

import application.modele.Environnement;
import application.modele.exception.ErreurObjetIntrouvable;

public class Slime extends Ennemis {
	

	public Slime(int pv, int x, int y, int vitesseDeplacement,Environnement environnement,int[] taille, int degat, boolean vole)  {
		super(pv, x, y, vitesseDeplacement,environnement,taille, degat, vole);
	}
	
	public Slime(Environnement environnement,int[] taille, int degat, boolean vole)  {
		super(environnement,taille, degat, vole);
	}
	
	public Slime(int x, int y, int vitesse ,Environnement env)  {
		super(x,y, vitesse , env);
	}
	
	public  void agir(Object controleur) throws ErreurObjetIntrouvable{
		
	}

}
