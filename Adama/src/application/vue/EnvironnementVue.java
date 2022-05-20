package application.vue;

import java.io.IOException;

import application.modele.Environnement;
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
//		int compteurColone=0;
//		int compteurLigne=0;
//		boolean quitter=false;
		System.out.println(env);
		String caractere = String.valueOf(env.getCarte().getMap().read());
		
		if (caractere!=",") {
			val=Integer.parseInt(caractere);
			carte.getChildren().add(choixTuile(val));	
		}
//		while(env.getCarte().getMap().hasNext() && !quitter) {
//			if (compteurColone<60) {
//				val=Integer.parseInt(env.getCarte().getMap().next());
//				carte.getChildren().add(choixTuile(val));	
//			}
//			else if(compteurLigne>=32) {
//				quitter = true;
//			}
//			else {
//				env.getCarte().getMap().nextLine();
//				compteurColone=-1;
//				compteurLigne++;
//			}
//			compteurColone++;
//		}
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
