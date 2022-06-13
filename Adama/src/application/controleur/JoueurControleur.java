package application.controleur;

import application.modele.Environnement;
import application.modele.exception.ErreurInventairePlein;
import application.modele.outils.Hache;
import application.modele.outils.Pelle;
import application.modele.outils.Pioche;
import application.modele.personnages.Joueur;
import application.vue.JoueurVue;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;

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
				// TODO Alert
				Label message = new Label(e.getMessage());
				Alert a = new Alert(AlertType.WARNING, e.getMessage(), ButtonType.CLOSE);
				a.setTitle("Inventaire Plein");
				a.setHeaderText("Votre Inventaire est plein");
				a.getDialogPane().setPrefWidth(400);
				a.show();
				
			}
			break;
		default:
			break;
		}
	}
}
