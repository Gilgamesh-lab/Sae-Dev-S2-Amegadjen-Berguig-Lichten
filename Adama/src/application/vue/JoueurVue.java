package application.vue;

import application.modele.Joueur;
import javafx.geometry.NodeOrientation;
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
				orrientationSpriteGauche();
				perso.gauche(perso.Deplacement(0));
				break;
			case "d":
				orrientationSpriteDroite();
				perso.droite(perso.Deplacement(0));
				break;
			case "z":
				perso.saut(40);//car les image font 32 pixel de coté
				break;
			case "s":
				break;
		}
	}
	
	public void orrientationSpriteGauche() {
		if (sprite.effectiveNodeOrientationProperty().get()==NodeOrientation.RIGHT_TO_LEFT)
			sprite.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
	}
	
	public void orrientationSpriteDroite() {
		if (sprite.effectiveNodeOrientationProperty().get()==NodeOrientation.LEFT_TO_RIGHT)
			sprite.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
	}
}
