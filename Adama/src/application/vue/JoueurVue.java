package application.vue;

import javafx.geometry.NodeOrientation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class JoueurVue {

	private ImageView sprite;


	public JoueurVue() {
		this.sprite =new ImageView("ressource/perso.png");
	}

	public ImageView getSprite() {
		return sprite;
	}
	
	public void setSprite(String image) {
		this.sprite.setImage(new Image(image));
	}
	
	public void sautVue(int temps) {
		if (temps<1) 
			this.sprite.setImage(new Image("ressource/persoAccroupi.jpg"));
		else if (temps==10) 
			this.sprite.setImage(new Image("ressource/perso.png"));
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
