package application.controleur;

import application.modele.Carte;
import application.modele.Inventaire;
import application.modele.Item;
import application.modele.outils.Sceau;
import application.vue.InventaireVue;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
		while(c.next()) {
			for(Item ajout : c.getAddedSubList()) {
				image = invVue.chargerImage(ajout.getClass().getSimpleName());
				Pane tuile = (Pane) inv.getChildren().get(compteur);
				ImageView b = ((ImageView) tuile.getChildren().get(0));
				if(ajout instanceof Sceau) {
					ChangeListener<Boolean> ecouteurDeSeau =  ((obs,old,nouv)-> {
						if(((Sceau) ajout).EstRempli())
							b.setImage(new Image("ressource/seau_plein.png"));
						else
							b.setImage(new Image("ressource/seau_vide.png"));
					});
					((Sceau) ajout).EstRempliProperty().addListener(ecouteurDeSeau);

				}
				b.setImage(image);
				Inventaire inventaire = new Inventaire(20);
				inventaire.setItems((ObservableList<Item>) c.getList());
				compteur = inventaire.getTaille();
			}

			for(Item suppression : c.getRemoved()) {
				Pane tuile = (Pane) inv.getChildren().get(c.getFrom());
				tuile.setStyle("-fx-background-color: #bbbbbb;-fx-border-style:none;-fx-border-width:0px;");
				ImageView img = (ImageView) tuile.getChildren().get(0);
				img.setImage(null);

			}

		}
	}
}