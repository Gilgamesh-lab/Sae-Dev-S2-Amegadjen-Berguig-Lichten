package application.modele.personnages;

import application.controleur.CerfControleur;
import application.modele.Carte;
import application.modele.Environnement;
import application.modele.exception.ErreurObjetIntrouvable;

public class Cerf extends Personnage {
	
	public final static int[] TAILLE = {2,2};

	public Cerf(Environnement environnement) {
		super(10, 10, 10, 10, environnement, TAILLE);
	}
	
	public Cerf(int x,int y,Environnement environnement) {
		super(10, x, y, 10, environnement, TAILLE);
	}
	
	public  void agir() throws ErreurObjetIntrouvable{
		
	}
	
	public void agir(CerfControleur cerfControleur) throws ErreurObjetIntrouvable {
		if(this.estPrèsDuJoueur(Carte.TAILLE_BLOCK * 5,1)) {
			if(this.ouSeTrouveLeJoueur()) {// si le joueur se trouve à sa droite
					this.gauche();
				}
				else {
					this.droite();
			}
		}
	}
	
	

}
