package application.controleur;

import application.modele.Item;
import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class InventaireControleur implements ListChangeListener<Item> {

	private HBox inv;
	
	public InventaireControleur(HBox inventaire) {
		inv = inventaire;
		System.out.println(inv.getChildren().get(0).getClass());
	}
	@Override
	public void onChanged(Change<? extends Item> c) {
		int compteur=0;
		ImageView image = null;
		while(c.next()) {
			for(Item ajout : c.getAddedSubList()) {
				image = chargerImage(compteur, compteur/2);
				switch (ajout.getClass().getSimpleName()) {
				case "Pierre":
					image.setImage(new Image("ressource/pierreIcone.png"));
					break;
				case "Terre":
					image.setImage(new Image("ressource/marron.png"));
				default:
					break;
				}
				compteur++;
			}
			
			for(Item suppression : c.getRemoved()) {
				//TODO
			}
		}
		
	}
	
	private ImageView chargerImage(int colonne, int ligne) {
		Pane c = (Pane) inv.getChildren().get(colonne);
		return ((ImageView) c.getChildren().get(ligne));
	}

}
