package application.modele.personnages;

import java.io.IOException;

import application.modele.Environnement;
import application.modele.exception.ErreurObjetIntrouvable;

public abstract class Ennemis extends Personnage{
	private int degat;

	public Ennemis(int pv, int x, int y, int vitesseDeplacement, Environnement environnement, int[] taille, int degat) {
		super(pv, x, y, vitesseDeplacement, environnement, taille);
		this.degat = degat;
	}

	public Ennemis(Environnement environnement,int[] taille, int degat) {
		super(10, 10, 10, 10, environnement, taille);
		this.degat = degat;
	}

	public Ennemis(int x, int y,int vitesseDeplacement,Environnement environnement, int[] taille) {


		super(10, x, y, 10, environnement, taille);

		this.degat = 1;
	}

	public int getDegat() {
		return this.degat;
	}

	public void attaquer() throws ErreurObjetIntrouvable {
		if(this.ouSeTrouveLeJoueur()) {
			this.droite();
		}
	}

	public void sauterSurJoueur() throws ErreurObjetIntrouvable {
		this.sauterEnDirection(this.ouSeTrouveLeJoueur());
	}
}
