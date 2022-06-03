package application.modele.personnages;

import application.modele.Environnement;
import application.modele.exception.ErreurObjetIntrouvable;

public class Cerf extends Personnage {

	public Cerf(Environnement environnement,int[] taille) {
		super(10, 10, 10, 10, environnement, taille);
	}
	
	public void fuirJoueur() throws ErreurObjetIntrouvable {
		if(this.ouSeTrouveLeJoueur()) {// si le joueur se trouve à sa droite
			this.gauche();
		}
		else {
			this.droite();
		}
	}
	
	

}
