package application.controleur;

import application.modele.Item;
import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public class InventaireControleur implements ListChangeListener<Item> {

	private static int compteur =0;
	private TilePane inv;

	public InventaireControleur(TilePane inventaire) {
		inv = inventaire;
		System.out.println(inv.getChildren().get(0).getClass());
	}

	@Override
	public void onChanged(Change<? extends Item> c) {
		Image image = null;
		if (compteur==20) {
			compteur=0;
		}
		while(c.next()) {
			for(Item ajout : c.getAddedSubList()) {
//				image = chargerImage(compteur, compteur/2);
				switch (ajout.getClass().getSimpleName()) {
				case "Pierre":
					image = new Image("ressource/pierreIcone.png");
					break;
				case "Terre":
					image = new Image("ressource/terreIcone.png");
					break;
				case "Bois":
					image = new Image("ressource/boisIcone.png");
					break;
				default:
					break;
				}
				Pane tuile = (Pane) inv.getChildren().get(compteur);
				((ImageView) tuile.getChildren().get(0)).setImage(image);
				compteur++;
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
