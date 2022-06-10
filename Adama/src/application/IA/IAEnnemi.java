//package application.IA;
//
//
//
//import application.modele.exception.ErreurObjetIntrouvable;
//
//import application.modele.personnages.Slime;
//import application.vue.MonstreVue;
//import application.vue.PNJVue;
//
//public class IAEnnemi {
//
//	private Slime slime;
//	private PNJVue Vue;
//	private boolean saut;
//	private int tempsSaut;
//
//	public IAEnnemi(Slime slime, PNJVue monstreVue) {
//		this.slime=slime;
//		this.slimeVue=monstreVue;
//		saut = false;
//		tempsSaut = 0;
//	}
//
//	
//
//	public void agir() throws ErreurObjetIntrouvable {
//		if(!slime.estPrèsDuJoueur(32)) {
//			if(slime.ouSeTrouveLeJoueur()) {// si le joueur se trouve à sa droite
//				slimeVue.orrientationSpriteDroite();
//				slime.translationX(-1);
//				if(!slime.toucheX(true)) {
//					slime.sauter();
//				}
//			}
//			else {
//				slimeVue.orrientationSpriteGauche();
//				slime.translationX(1);
//				if(!slime.toucheX(false)) {
//					slime.sauter();
//				}
//			}
//		}
//		
//		else{
//			//this.slime.getEnvironnement().getJoueur().meurt();
//			if(!slime.toucheY(false)) {
//				this.saut = true;
//				System.out.println("saut" + this.tempsSaut);
//			}
//		}
//			
//	}
//
//	public boolean isSaut() {
//		return saut;
//	}
//	
//	public void meurt() {
//		this.slimeVue.getSprite();
//		this.slime.getEnvironnement().supprimer(this.slime);
//	}
//
//	public void setSaut(boolean saut) {
//		this.saut = saut;
//	}
//
//	public int getTempsSaut() {
//		return tempsSaut;
//	}
//	
//	public void incremterTempsSaut() {
//		this.tempsSaut+=1;
//	}
//	
//	public void incremterTempsSaut(int val) {
//		this.tempsSaut+= val;
//	}
//	
//	public void reinisialiseTempsSaut() {
//		this.tempsSaut = 0;
//	}
//
//}