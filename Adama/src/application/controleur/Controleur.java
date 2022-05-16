package application.controleur;

import java.net.URL;
import java.util.ResourceBundle;

import application.modele.Environnement;
import application.vue.EnvironnementVue;
import javafx.beans.property.IntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.TilePane;

public class Controleur implements Initializable{

	@FXML
	private TilePane carte;
	
	@FXML
	private ImageView perso;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EnvironnementVue env = new EnvironnementVue(new Environnement(), carte);
		env.creerEnvironnement();
	}
	@FXML
	void onAction(KeyEvent event) {
		
	}
}
