package application.vue;

import application.modele.Joueur;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.NodeOrientation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

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
	
	public void sautVue() {
		
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
