package application.controleur;

import java.net.URL;
import java.util.ResourceBundle;

import application.modele.Environnement;
import application.modele.Joueur;
import application.vue.EnvironnementVue;
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
	private Environnement envJoueur;
	private EnvironnementVue env;
	
	@FXML
	void touchePresse(KeyEvent event) {
		System.out.println("event");
		String touchePressé = event.getCode().toString().toLowerCase();
        System.out.println(touchePressé);
        switch (touchePressé) {
	        case "q":
	        	//System.out.println(pers.getX());
	        	perso.gauche(EnvironnementVue.PIXEL);
	            break;
	        case "d":
	            perso.droite(EnvironnementVue.PIXEL);
	            break;
	        case "z":
//	            perso.setTranslateY(32);
	            break;
	        case "s":
//	            perso.setTranslateY(-32);
	            break;
        }
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		envJoueur =new Environnement();
		env = new EnvironnementVue(envJoueur, carte);
		perso  = new Joueur(7, 50, 50, envJoueur);
		ImageView persoVue2 = new ImageView("ressource/perso.png");
		plateau.getChildren().add(persoVue2);
		env.creerEnvironnement();
		persoVue2.xProperty().bind(perso.xProperty());
		persoVue2.yProperty().bind(perso.yProperty());
	}
}
