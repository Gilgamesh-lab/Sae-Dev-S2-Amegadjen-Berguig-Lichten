package application.controleur;

import java.net.URL;
import java.util.ResourceBundle;

import application.modele.Carte;
import application.modele.Environnement;
import application.modele.Joueur;
import application.vue.EnvironnementVue;
import application.vue.JoueurVue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

public class Controleur implements Initializable{
	
    @FXML
    private Pane plateau;
	@FXML
	private TilePane carte;
	
	
	private Joueur perso;
	private JoueurVue persoVue;
	private Environnement env;
	private EnvironnementVue envVue;
	
	
	@FXML
	void touchePresse(KeyEvent event) {
		
		System.out.println("event");
		String touchePresse = event.getCode().toString().toLowerCase();
        System.out.println(touchePresse);
        persoVue.touchePresse(touchePresse, perso);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		env =new Environnement(new Carte());
		envVue = new EnvironnementVue(env, carte);
		perso  = new Joueur(7, 500, 450, env);
		persoVue = new JoueurVue(perso);
		plateau.getChildren().add(persoVue.getSprite());
		envVue.creerEnvironnement();
		persoVue.getSprite().xProperty().bind(perso.xProperty());
		persoVue.getSprite().yProperty().bind(perso.yProperty());
		persoVue.getSprite().setFitHeight(64);
		persoVue.getSprite().setFitWidth(32);
	}
}
