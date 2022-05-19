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
	private Timeline gameLoop;
	private int temps;

	public JoueurVue(Joueur joueur, Timeline gameLoop) {
		this.joueur = joueur;
		this.sprite = new ImageView("ressource/perso.png");
		this.gameLoop = gameLoop;
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
				this.initAnimationSaut();
				gameLoop.play();
//				perso.saut(40);//car les image font 32 pixel de coté
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
	
	private void initAnimationSaut() {
		gameLoop = new Timeline();
		temps=0;
		KeyFrame kf = new KeyFrame(Duration.seconds(0.017), 
			(ev -> { //Il ne detecte pas le lambdas a debug
				if(temps==100) { 
					System.out.println("fini");
					gameLoop.stop();
				}
				else if (temps<10) {
					System.out.println("Accroupi");
//					this.sprite.setImage(new Image("ressource/persoAccroupi.jpg"));
				}
				else if (temps<90) {
					System.out.println("Poing levé");
					this.joueur.saut(45);
//					this.sprite.setImage(new Image("ressource/perso.png"));
				}
				else {
					System.out.println("Accroupi");
					
				}
				temps++;							
		}));
		
	}
}
