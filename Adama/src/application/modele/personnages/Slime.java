package application.modele.personnages;

import application.modele.Environnement;
import application.modele.exception.ErreurObjetIntrouvable;

public class Slime extends Ennemis {
	
	private static final int[] TAILLE = {1,1};
	
	public Slime(int pv, int x, int y, int vitesseDeplacement,Environnement environnement, int degat, boolean vole)  {
		super(pv, x, y, vitesseDeplacement,environnement,TAILLE, degat, vole);
	}
	
	public Slime(Environnement environnement, int degat, boolean vole)  {
		super(environnement,TAILLE, degat, vole);
	}
	
	public Slime(int x, int y, int vitesse ,Environnement env)  {
		super(x, y, vitesse, env, TAILLE);
	}
	
	public  void agir(Object controleur) throws ErreurObjetIntrouvable{
		
	}

}
