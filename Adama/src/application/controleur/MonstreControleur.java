package application.controleur;



import application.modele.exception.ErreurObjetIntrouvable;

import application.modele.personnages.Slime;
import application.vue.MonstreVue;

public class MonstreControleur {

	private Slime slime;
	private MonstreVue slimeVue;
	private boolean saut;
	private int tempsSaut;
	private boolean avancer;

	public MonstreControleur(Slime slime, MonstreVue monstreVue) {
		this.slime=slime;
		this.slimeVue=monstreVue;
		saut = false;
		tempsSaut = 0;
		avancer = false;
	}

	

	public void agir() throws ErreurObjetIntrouvable {
		if(!slime.getEnvironnement().getJoueur().estMort()) {
			if(!slime.toucheY(false)) {
				if(!slime.estPrèsDuJoueur(32, 64)) {
					if(slime.ouSeTrouveLeJoueur()) {// si le joueur se trouve à sa droite
						slimeVue.orrientationSpriteDroite();
						slime.translationX(-1);
						if(!slime.toucheX(true) && avancer) {
							int i = 0;
							System.out.println("sauter");
							while(!slime.toucheX(true) && i < slime.getHauteurSaut()){
								slime.translationY(slime.getHauteurSaut()/8);
								i += 8;
							}
							
							avancer = false;
							
						}
						if(slime.toucheX(true) && !avancer) {
							avancer = true;
						}
					}
					
					
					else {
						slimeVue.orrientationSpriteGauche();
						slime.translationX(1);
						if(!slime.toucheX(false)) {
							System.out.println("sauter");
							slime.sauter();
						}
					}
				}
				
				else {
					saut = true;
					this.slime.getEnvironnement().getJoueur().decrementerPv(2);
//					for (int k = 0 ; k < 32 ; k++) {
//						slime.getEnvironnement().getJoueur().translationX(-2);
//					}
		//			if(!slime.toucheY(false)) {
		//				this.saut = true;
		//				System.out.println("saut" + this.tempsSaut);
		//			}
				}
			}
		}
			
	}

	public boolean isSaut() {
		return saut;
	}
	
	public void meurt() {
		this.slimeVue.getSprite();
		this.slime.getEnvironnement().supprimer(this.slime);
	}

	public void setSaut(boolean saut) {
		this.saut = saut;
	}

	public int getTempsSaut() {
		return tempsSaut;
	}
	
	public void incremterTempsSaut() {
		this.tempsSaut+=1;
	}
	
	public void incremterTempsSaut(int val) {
		this.tempsSaut+= val;
	}
	
	public void reinisialiseTempsSaut() {
		this.tempsSaut = 0;
	}

}