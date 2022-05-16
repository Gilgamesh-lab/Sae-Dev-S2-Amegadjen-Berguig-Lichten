package application.vue;

import application.modele.Environnement;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class EnvironnementVue {
	
	private final static int PIXEL=32;//Ecran de l'iut 4*480 largeur 1920*1080 16/9
	private TilePane carte;
	private Environnement env;
	
	public EnvironnementVue(Environnement env, TilePane carte) {
		this.carte=carte;
		this.env=env;
	}
	
	public void creerEnvironnement() {
		carte.setPrefHeight(PIXEL);
		for(int[] tab : Environnement.grille())
			for (int val : tab)
				carte.getChildren().add(choixTuile(val));
	}
	
	private ImageView choixTuile(int val) {
		ImageView img;
		if (val==0)
			img = new ImageView("ressources/bleu.jpeg");
		else if (val==1)
			img = new ImageView("ressources/marron.jpeg");
		else
			img = new ImageView("ressources/herbe.png");
		img.setFitHeight(PIXEL);
		img.setFitWidth(PIXEL);
		return img;
	}
}
