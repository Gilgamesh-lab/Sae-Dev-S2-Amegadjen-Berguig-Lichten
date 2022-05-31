package application.controleur;

import java.io.IOException;

import application.modele.Ressources.Terre;
import application.modele.personnage.Joueur;
import application.vue.JoueurVue;
import javafx.scene.input.MouseEvent;

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
			try {
				perso.sauter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
