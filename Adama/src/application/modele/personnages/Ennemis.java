package application.modele.personnages;

import java.io.IOException;

import application.modele.Environnement;
import application.modele.exception.ErreurObjetIntrouvable;

public abstract class Ennemis extends Personnage{
	private int degat;
	private boolean vole;
	

	public Ennemis(int pv, int x, int y, int vitesseDeplacement, Environnement environnement, int[] taille, int degat, boolean vole) {
		super(pv, x, y, vitesseDeplacement, environnement, taille);
		this.degat = degat;
		this.vole = vole;
	}
	
	public Ennemis(Environnement environnement,int[] taille, int degat, boolean vole) {
		super(10, 10, 10, 10, environnement, taille);
		this.degat = degat;
		this.vole = vole;
	}
	
	public Ennemis(int x, int y,int vitesseDeplacement,Environnement environnement, int[] taille) {
		super(10, x, y, 1, environnement, taille);
		this.degat = 1;
		this.vole = false;
	}
	
	public int getDegat() {
		return this.degat;
	}
	
	
	public boolean peutVoler() {
		return this.vole;
	}
	
	public void attaquer() throws ErreurObjetIntrouvable {
		if(this.ouSeTrouveLeJoueur()) {
			this.droite();
		}
	}
	
	
	
	public void sauterSurJoueur() throws ErreurObjetIntrouvable, IOException {
		this.sauter(this.ouSeTrouveLeJoueur());
	}
	
	
	
	
	
	
	
	
	
	
	

}
