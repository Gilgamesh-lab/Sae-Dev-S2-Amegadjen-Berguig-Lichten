package application.modele.personnages;

import application.modele.Environnement;
import application.modele.Inventaire;
import application.modele.exception.ErreurObjetIntrouvable;
import application.modele.ressources.Pierre;

public class Slime extends Ennemis {

	private static final int PV = 15;
	public static final int[] TAILLE = {1,1};
	private static final int DEGATS = 5;
	private static final int VITESSE = 1;
	private static final int HAUTEUR_SAUT = 20;
	private static final Inventaire INVENTAIRE = new Inventaire(new Pierre(0));

	public Slime(int x, int y,Environnement environnement)  {
		super(PV, x, y, VITESSE,environnement,INVENTAIRE, HAUTEUR_SAUT, TAILLE, DEGATS);
	}
	
	public void agir() throws ErreurObjetIntrouvable{
		if(!this.getEnvironnement().getJoueur().estMort()) {
			if (!this.touchePasY(false)) {
				if(!this.estPrèsDuJoueur(32, 64)) {
					if(this.ouSeTrouveLeJoueur()) {// si le joueur se trouve à sa droite
						this.droite();
						if(!this.touchePasX(true))
							this.sauterEnDirection(true);
					}
					else {
						this.gauche();
						if(!this.touchePasX(false))
							this.sauterEnDirection(false);
					}
				}
			}
		}
	}

	
	
	public  void agir(Object controleur) throws ErreurObjetIntrouvable{
		
	}

}
