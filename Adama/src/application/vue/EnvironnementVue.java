package application.vue;

import application.modele.Environnement;
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
	
	public void creerEnvironnement() {
//		carte.setPrefHeight(PIXEL);
//		for(int[] tab : env.getCarte().getCarteTab())
//			for (int val : tab)
//				carte.getChildren().add(choixTuile(val));
		int val;
		int compteurColone=0;
		int compteurLigne=0;
		boolean quitter=false;
		while(env.getCarte().getMap().hasNext() && !quitter) {
			if (compteurColone<60) {
				val=Integer.parseInt(env.getCarte().getMap().next());
				carte.getChildren().add(choixTuile(val));	
			}
			else if(compteurLigne>=32) {
				quitter = true;
			}
			else {
				env.getCarte().getMap().nextLine();
				compteurColone=-1;
				compteurLigne++;
			}
			compteurColone++;
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
			System.out.println("La valeur" + val + "ne correpond à aucune tuiles !");
			break;
		}
		return img;
	}
}
