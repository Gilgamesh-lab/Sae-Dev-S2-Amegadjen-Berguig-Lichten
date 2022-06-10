package application.controleur;

import application.modele.personnages.Joueur;
import application.vue.JoueurVue;

public class JoueurControleur {

	private Joueur perso;
	private JoueurVue persoVue;
	private boolean saut;
	private int tempsSaut;

	public JoueurControleur(Joueur perso, JoueurVue persoVue) {
		this.perso=perso;
		this.persoVue=persoVue;
		saut = false;
		tempsSaut = 0;
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
			if(!perso.toucheY(false))
				saut = true;
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

	public boolean isSaut() {
		return saut;
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
	
	public void reinisialiseTempsSaut() {
		this.tempsSaut = 0;
	}

}
