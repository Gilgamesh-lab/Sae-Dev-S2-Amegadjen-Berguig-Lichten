package application.controleur;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import application.modele.Carte;
import application.modele.Environnement;
import application.modele.Joueur;
import application.vue.EnvironnementVue;
import application.vue.JoueurVue;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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
	private Environnement env;
	private EnvironnementVue envVue;
	
	
	@FXML
	void touchePresse(KeyEvent event) {
		String touchePresse = event.getCode().toString().toLowerCase();
        //System.out.println(touchePresse);
        persoVue.touchePresse(touchePresse, perso);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			env =new Environnement();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		envVue = new EnvironnementVue(env, carte);
		perso  = new Joueur(500, 420, env);
		persoVue = new JoueurVue(perso, gameLoop);
		plateau.getChildren().add(persoVue.getSprite());
		envVue.creerEnvironnement();
		persoVue.getSprite().xProperty().bind(perso.xProperty());
		persoVue.getSprite().yProperty().bind(perso.yProperty());
		persoVue.getSprite().setFitHeight(64);
		persoVue.getSprite().setFitWidth(32);
	}

	
}
