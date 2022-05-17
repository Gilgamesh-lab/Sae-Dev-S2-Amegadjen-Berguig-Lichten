package application.vue;

import application.modele.Joueur;
import javafx.scene.image.ImageView;

public class JoueurVue {

	private Joueur joueur;
	private ImageView sprite;

	public JoueurVue(Joueur joueur) {
		this.joueur = joueur;
		this.sprite = new ImageView("ressource/perso.png");
	}

	public ImageView getSprite() {
		return sprite;
	}

	public void touchePresse(String touchePresse, Joueur perso) {
		switch (touchePresse) {
			case "q":
				perso.gauche(perso.Deplacement(0));
				break;
			case "d":
				perso.droite(perso.Deplacement(0));
				break;
			case "z":
				break;
			case "s":
				break;
		}
	}
}
