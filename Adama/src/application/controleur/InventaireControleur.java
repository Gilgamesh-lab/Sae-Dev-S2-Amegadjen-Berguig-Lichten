package application.controleur;

import application.modele.Item;
import application.modele.outils.Sceau;
import application.modele.ressources.Ressource;
import application.vue.InventaireVue;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public class InventaireControleur implements ListChangeListener<Item> {

	private static int compteur =0;
	private TilePane inv;
	private InventaireVue invVue;

	public InventaireControleur(TilePane inventaire) {
		inv = inventaire;
		invVue = new InventaireVue();
	}

	@Override
	public void onChanged(Change<? extends Item> c) {
		Image image = null;
		if (compteur==20) {
			compteur=0;
		}
		while(c.next()) {
			for(Item ajout : c.getAddedSubList()) {
				image = invVue.chargerImage(ajout.getClass().getSimpleName());
				Pane tuile = (Pane) inv.getChildren().get(compteur);
				ImageView b = ((ImageView) tuile.getChildren().get(0));
				Label nombre = (Label) tuile.getChildren().get(1);
				nombre.setVisible(true);
				if(ajout instanceof Sceau) {
					ChangeListener<Boolean> ecouteurDeSceau =  ((obs,old,nouv)-> {
						if(((Sceau) ajout).EstRempli())
							b.setImage(new Image("ressource/sceau_plein.png"));
						else
							b.setImage(new Image("ressource/sceau_vide.png"));
					});
					((Sceau) ajout).EstRempliProperty().addListener(ecouteurDeSceau);
					
				}
				else if(ajout instanceof Ressource) {
					nombre.textProperty().bind(((Ressource) ajout).nombreProperty().asString());
					
				}
				b.setImage(image);
				compteur++;
			}
			for(Item suppression : c.getRemoved()) {
				Pane tuile = (Pane) inv.getChildren().get(c.getTo());
				tuile.setStyle("-fx-background-color: #bbbbbb;-fx-border-style:none;-fx-border-width:0px;");
				ImageView img = (ImageView) tuile.getChildren().get(0);
				Label nombre = (Label) tuile.getChildren().get(1);
				nombre.setVisible(false);
				nombre.textProperty().unbind();
				img.setImage(null);
				
			}

		}

	}
	

}
