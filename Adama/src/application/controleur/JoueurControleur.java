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
			perso.gauche(perso.Deplacement(0));
			break;
		case "d":
			persoVue.orrientationSpriteDroite();
			perso.droite(perso.Deplacement(0));
			break;
		case "z":
			perso.saut(15);
			break;
		case "s":
			break;
		}
	}
	
}
