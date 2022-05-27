package application.vue;

import java.io.IOException;


import application.modele.Environnement;
import application.modele.Pierre;
import application.modele.Ressource;
import application.modele.Terre;
import javafx.scene.ImageCursor;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

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
		int tailleMap = (env.getCarte().getHauteur()) * (env.getCarte().getLargeur());
		int largeur = env.getCarte().getLargeur();
		Ressource block;
		for(int i=0; i<tailleMap; i++) {
			block= env.getCarte().getBlockMap().get(i);
			val=blockPourVal(block, i, largeur);
			this.carte.getChildren().add(choixTuile(val));	
		}
		ImageView test = new ImageView();
//		ImageCursor changer l'image de la souris
	}
	
	public int blockPourVal(Ressource bloc, int indice, int largeur) {
		int val;
		if (bloc==null)
			val=0;
		else if (bloc instanceof Terre && indice>largeur && env.getCarte().getBlockMap().get(indice-largeur)==null)
			val=1;				
		else if (bloc instanceof Pierre)
			val=3;
		else
			val=2;
		return val;
	}
	
	public ImageView choixTuile(int val) {
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
	
//	public ListChangeListener<ImageView> miseAJourMap(ObservableList<Ressource> obs, Ressource old, Ressource nouv){
//		System.out.println("Changement");
//		while ()
//		int val = blockPourVal(nouv, obs.indexOf(nouv), env.getCarte().getLargeur());
//		return null;
//	}
}
