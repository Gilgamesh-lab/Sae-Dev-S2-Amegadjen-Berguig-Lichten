package application.modele.personnages;

import application.modele.Environnement;

public class Slime extends Ennemis {


	private static final int[] TAILLE = {1,1};
	private static final int DEGATS = 5;
	private static final int VITESSE = 4;

	public Slime(int pv, int x, int y,Environnement environnement)  {
		super(pv, x, y, VITESSE,environnement,TAILLE, DEGATS, false);
		System.out.println("constructeur 1 : x = "+this.getX()+", y = "+this.getY());
	}

	public Slime(Environnement environnement)  {
		super(environnement,TAILLE, DEGATS, false);
		System.out.println("constructeur 2 : x = "+this.getX()+", y = "+this.getY());
	}

	public Slime(int x, int y,Environnement env)  {
		super(x, y, VITESSE, env, TAILLE);
		System.out.println("constructeur 3 : x = "+this.getX()+", y = "+this.getY());
	}
	
	public  boolean agir(){
		System.out.println("x = "+this.getX()+", y = "+this.getY());
		if(this.ouSeTrouveLeJoueur()) {// si le joueur se trouve à sa droite
			this.droite();
			if(!this.touchePasX(true))
				this.sauterEnDirection(true);
			return true;
		}
		else {
			this.gauche();
			if(!this.touchePasX(false))
				this.sauterEnDirection(false);
			return false;
		}
	}
}