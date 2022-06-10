//package application.IA;
//
//
//
//import application.modele.Carte;
//import application.modele.Environnement;
//import application.modele.exception.ErreurObjetIntrouvable;
//import application.modele.personnages.Cerf;
//import application.vue.CerfVue;
//
//public class IACerf {
//
//	private Cerf cerf;
//	private CerfVue cerfVue;
//
//
//	public IACerf(Cerf cerf, CerfVue cerfVue) {
//		this.cerf=cerf;
//		this.cerfVue=cerfVue;
//	}
//
//	
//
//	public void agir() throws ErreurObjetIntrouvable {
//		if(cerf.estPrèsDuJoueur(Carte.TAILLE_BLOCK * 5)) {
//			if(cerf.ouSeTrouveLeJoueur()) {// si le joueur se trouve à sa droite
//				cerfVue.orrientationSpriteGauche();
//				cerf.gauche();
//				if(!cerf.toucheX(false)) {
//					cerf.sauter();
//				}
//			}
//			else {
//				cerfVue.orrientationSpriteDroite();
//				cerf.droite();
//				if(!cerf.toucheX(true)) {
//					cerf.sauter();
//				}
//			}
//		}
//	}
//}
