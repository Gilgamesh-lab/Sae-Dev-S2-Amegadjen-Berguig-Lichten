package application.controleur;

import application.modele.Environnement;
import application.modele.exception.ErreurInventairePlein;
import application.modele.outils.Hache;
import application.modele.outils.Pelle;
import application.modele.outils.Pioche;
import application.modele.personnages.Joueur;
import application.vue.JoueurVue;

public class JoueurControleur {

	private Joueur perso;
	private JoueurVue persoVue;
	private Environnement env;


	public JoueurControleur(Joueur perso, JoueurVue persoVue, Environnement env) {
		this.perso=perso;
		this.persoVue=persoVue;
		this.env=env;
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
			if(!perso.touchePasY(false))
				perso.sauter();
			break;
		case "s":
			break;
		case "f":
			perso.equiper(new Pelle(env));
			break;
		case "g":
			perso.equiper(new Pioche(env));
			break;
		case "h":
			perso.equiper(new Hache(env));
			break;
		}
	}

	public void sourisPresse(String click, int emplacement) {
		switch (click) {
		case "PRIMARY":
			try {
				perso.utiliserMain(emplacement);
			} catch (ErreurInventairePlein e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}
}
