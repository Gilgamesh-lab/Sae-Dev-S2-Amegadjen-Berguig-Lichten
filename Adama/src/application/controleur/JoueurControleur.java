package application.controleur;

import application.modele.Joueur;
import application.vue.JoueurVue;

public class JoueurControleur {
	
	private Joueur perso;
	private JoueurVue persoVue;
	
	public JoueurControleur(Joueur perso, JoueurVue persoVue) {
		this.perso=perso;
		this.persoVue=persoVue;
	}
	
	public void touchePresse(String touchePresse) {
		switch (touchePresse) {
		case "q":
			persoVue.orrientationSpriteGauche();
			perso.gauche();
			break;
		case "d":
			persoVue.orrientationSpriteDroite();
			perso.droite();
			break;
		case "z":
			perso.saut(15);
			break;
		case "s":
			break;
		}
	}
	
	public void sourisPresse(String click, int emplacement) {
		switch (click) {
		case "PRIMARY":
			perso.utiliserMain(emplacement);
			break;
		default:
			break;
		}
	}
	
}
