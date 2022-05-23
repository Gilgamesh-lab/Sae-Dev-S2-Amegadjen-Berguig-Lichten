package application.vue;

import java.io.IOException;

import application.modele.Environnement;
import application.modele.Pierre;
import application.modele.Ressource;
import application.modele.Terre;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

public class EnvironnementVue {
	
//	public final static int PIXEL=32;//Ecran de l'iut 4*480 largeur 1920*1080 16/9
	private TilePane carte;
	private Environnement env;
	
	public EnvironnementVue(Environnement env, TilePane carte) {
		this.carte=carte;
		this.env=env;
	}
	
	public void creerEnvironnement() throws IOException {
		int val;
		int tailleMap = (env.getCarte().getHauteur()-1) * (env.getCarte().getLargeur()-1);
		int largeur = env.getCarte().getLargeur();
		Ressource block;
		for(int i=0; i<tailleMap; i++) {
			block= env.getCarte().getBlockMap().get(i);
			if (block==null)
				val=0;
			else if (block instanceof Terre && i>largeur && env.getCarte().getBlockMap().get(i-largeur)==null)
				val=1;				
			else if (block instanceof Pierre)
				val=3;
			else
				val=2;
			this.carte.getChildren().add(choixTuile(val));	
		}
	}
	
	private ImageView choixTuile(int val) {
		ImageView img = null;
		switch(val){
		case 0:
			img = new ImageView("ressource/bleu.jpeg");
			break;
		case 1:
			img = new ImageView("ressource/herbe.jpeg");
			break;
		case 2:
			img = new ImageView("ressource/marron.jpeg");
			break;
		case 3:
			img = new ImageView("ressource/Granit.jpeg");
			break;
		case 4:
			img = new ImageView("ressource/nuage.jpg");
			break;
		case 5:
			img = new ImageView("ressource/Soleil.jpg");
			break;
		default:
			System.out.println("La valeur" + val + " ne correpond à aucune tuiles !");
			break;
		}
		return img;
	}
}
