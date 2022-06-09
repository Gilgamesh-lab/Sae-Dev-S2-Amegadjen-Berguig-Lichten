package application.controleur;

import application.modele.Item;
import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class InventaireControleur implements ListChangeListener<Item> {

	private HBox inv;
	private static int compteurColonne=0;
	private static int compteurLigne=0;
	
	public InventaireControleur(HBox inventaire) {
		inv = inventaire;
		System.out.println(inv.getChildren().get(0).getClass());
	}
	
	@Override
	public void onChanged(Change<? extends Item> c) {
		ImageView image = new ImageView();
		if (compteurColonne==4) {
			compteurColonne=0;
			compteurLigne++;
		}
		if (compteurLigne==5)
			compteurLigne=0;
		while(c.next()) {
			for(Item ajout : c.getAddedSubList()) {
//				image = chargerImage(compteur, compteur/2);
				switch (ajout.getClass().getSimpleName()) {
				case "Pierre":
					image.setImage(new Image("ressource/pierreIcone.png"));
					break;
				case "Terre":
					image.setImage(new Image("ressource/terreIcone.jpeg"));
					break;
				case "Bois":
					image.setImage(new Image("ressource/boisIcone.png"));
					break;
				default:
					break;
				}
				VBox colonne = (VBox) inv.getChildren().get(compteurColonne);
				colonne.getChildren().set(compteurLigne, image);
				compteurColonne++;	
			}
			
			for(Item suppression : c.getRemoved()) {
				//TODO
			}
		
		}
		
	}
	
//	private ImageView chargerImage(int colonne, int ligne) {
//		VBox c = (VBox) inv.getChildren().get(colonne);
//		return ((ImageView) c.getChildren().get(ligne));
//	}

}
