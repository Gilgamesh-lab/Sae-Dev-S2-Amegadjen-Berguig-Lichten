package application.modele.personnages;

import application.modele.Carte;
import application.modele.Environnement;
import application.modele.exception.ErreurObjetIntrouvable;

public class Cerf extends Personnage {
	

	private static final int[] TAILLE = {2,2};
	private static final int VITESSE = 10;
	private static final int PV = 10; 


	public Cerf(Environnement environnement) {


		super(PV, 10, 10, VITESSE, environnement, TAILLE);

	}
	
	public Cerf(int x,int y,Environnement environnement) {
			super(PV, x, y, VITESSE, environnement, TAILLE);

	}

	public boolean agir() throws ErreurObjetIntrouvable {
		if(this.ouSeTrouveLeJoueur()) {// si le joueur se trouve à sa droite
			this.gauche();
			if(!this.touchePasX(false))
				this.sauter();
			return false;
		}
		else {
			this.droite();
			if(!this.touchePasX(true))
				this.sauter();
			return true;
		}
	}
}