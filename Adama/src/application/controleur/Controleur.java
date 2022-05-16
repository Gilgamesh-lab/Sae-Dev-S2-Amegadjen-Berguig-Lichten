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
import javafx.scene.layout.TilePane;

public class Controleur implements Initializable{
	
	@FXML
	private TilePane carte;
	@FXML
	private ImageView persoVue;
	
	private Environnement envJoueur =new Environnement();
	private EnvironnementVue env = new EnvironnementVue(envJoueur, carte);
	private Joueur perso = new Joueur(7, 0, 0, envJoueur);
	
	@FXML
	void onAction(KeyEvent event) {
		System.out.println("event");
		String touchePressé = event.getCode().toString().toLowerCase();
        System.out.println(touchePressé);
        switch (touchePressé) {
	        case "q":
	        	System.out.println(persoVue.getX());
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
		persoVue = new ImageView("ressource/perso.png");
		env.creerEnvironnement();
		perso.xProperty().bind(persoVue.xProperty());
		perso.yProperty().bind(persoVue.yProperty());
	}
}
