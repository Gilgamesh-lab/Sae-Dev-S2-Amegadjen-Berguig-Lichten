package application.controleur;

import java.net.URL;
import java.util.ResourceBundle;

import application.modele.Carte;
import javafx.beans.property.IntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class Controleur implements Initializable{

	private final static int PIXEL=32;//Ecran de l'iut 4*480 largeur 1920*1080 16/9
	private Carte monde;
	@FXML
	private TilePane carte;
	private ImageView marron;

	private ImageView choixTuile(int val) {
		ImageView img;
		if (val==0)
			img = new ImageView("ressource/bleu.jpeg");
		else if (val==1)
			img = new ImageView("ressource/marron.jpeg");
		else
			img = new ImageView("ressource/herbe.png");
		
		img.setFitHeight(PIXEL);
		img.setFitWidth(PIXEL);
		return img;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		carte.setPrefHeight(PIXEL);
		
		
		for(int[] tab : Carte.grille())
			for (int val : tab)
				carte.getChildren().add(choixTuile(val));
		
		/*
		carte.getChildren().add(choixTuile(0));
		carte.getChildren().add(choixTuile(1));
		carte.getChildren().add(choixTuile(1));
		carte.getChildren().add(choixTuile(0));
		carte.getChildren().add(choixTuile(0));
		System.out.println(carte.getChildren().size());
		*/
	}
}
