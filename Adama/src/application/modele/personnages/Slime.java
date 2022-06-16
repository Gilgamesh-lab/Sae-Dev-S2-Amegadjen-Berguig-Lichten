package application.modele.personnages;

import application.modele.Environnement;
import application.modele.exception.ErreurObjetIntrouvable;

public class Slime extends Ennemis {
	
	public final static int[] TAILLE = {1,1};
	public final static int PV = 15;
	public final static int VITESSEDEPLACEMENT = 1;

	public Slime(int pv, int x, int y, int vitesseDeplacement,Environnement environnement, int degat, boolean vole)  {
		super(pv, x, y, vitesseDeplacement,environnement,TAILLE, degat, vole);
	}
	
	public Slime(Environnement environnement,int[] taille, int degat, boolean vole)  {
		super(environnement,taille, degat, vole);
	}
	
	public Slime(int x, int y ,Environnement env)  {
		super(PV ,x,y, VITESSEDEPLACEMENT, env, TAILLE);
	}
	
	public  void agir(Object controleur) throws ErreurObjetIntrouvable{
		
	}

}
