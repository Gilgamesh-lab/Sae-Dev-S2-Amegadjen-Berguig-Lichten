package application.controleur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.modele.Environnement;
import application.modele.Joueur;
import application.vue.EnvironnementVue;
import application.vue.JoueurVue;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

public class Controleur implements Initializable{
	
    @FXML
    private Pane plateau;
	@FXML
	private TilePane carte;
	
	private Timeline gameLoop;
	private int temps;
	
	private Joueur perso;
	private JoueurVue persoVue;
	private JoueurControleur persoControleur;
	private Environnement env;
	private EnvironnementVue envVue;
	
	
	@FXML
	void touchePresse(KeyEvent event) {
		String touchePresse = event.getCode().toString().toLowerCase();
        /*
         * TODO
         * Mettre un switch pour gérer les action qui nécessite un wait (ex: pause avec echap)
         * et en default persoControleur.touchePresse(touchePresse)
         */
		System.out.println(touchePresse);
        persoControleur.touchePresse(touchePresse);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			env =new Environnement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		envVue = new EnvironnementVue(env, carte);
		perso  = new Joueur(320, 0, env);
		persoVue = new JoueurVue(perso);
		persoControleur = new JoueurControleur(perso, persoVue);
		plateau.getChildren().add(persoVue.getSprite());
		try {
			envVue.creerEnvironnement();
		} catch (IOException e) {
			e.printStackTrace();
		}
		persoVue.getSprite().xProperty().bind(perso.xProperty());
		persoVue.getSprite().yProperty().bind(perso.yProperty());
		persoVue.getSprite().setFitHeight(64);
		persoVue.getSprite().setFitWidth(32);
		
		initAnimation();
		gameLoop.play();
	}

	private void initAnimation() {
		gameLoop = new Timeline();
		temps=0;
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.017), 
				(ev -> { 
					if(temps==100)
						System.out.println("ok");
					else if(temps%4==0)
						try {
							perso.gravite();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					temps++;							
				})
				);
		gameLoop.getKeyFrames().add(kf);
	}	
}
