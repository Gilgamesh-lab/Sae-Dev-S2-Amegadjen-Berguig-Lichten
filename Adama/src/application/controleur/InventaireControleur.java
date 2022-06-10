package application.controleur;

import application.modele.Item;
import application.modele.outils.Seau;
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
				image = chargerImage(ajout.getClass().getSimpleName());
				Pane tuile = (Pane) inv.getChildren().get(compteur);
				ImageView b = ((ImageView) tuile.getChildren().get(0));
				if(ajout instanceof Seau) {
					ChangeListener<Boolean> ecouteurDeSeau =  ((obs,old,nouv)-> {
						if(((Seau) ajout).EstRempli())
							b.setImage(new Image("ressource/seau_plein.png"));
						else
							b.setImage(new Image("ressource/seau_vide.png"));
					});
					((Seau) ajout).EstRempliProperty().addListener(ecouteurDeSeau);
					
				}
				b.setImage(image);
				compteur++;
			}

			for(Item suppression : c.getRemoved()) {
				//TODO suppresion d'inventaire
			}

		}

	}
	/**
	 * cette méthode renvoie l'image correspondant à la class de ajout
	 * @param nomClass
	 * @return
	 */
	private Image chargerImage(String nomClass) {
		Image image = null;
		String chemin = "ressource/";
		String extention = ".png";
		switch (nomClass) {
		
		//chargement Ressource
		case "Pierre":
			image = new Image(chemin + "pierreIcone" + extention);
			break;
		case "Terre":
			image = new Image(chemin + "terreIcone" + extention);
			break;
		case "Bois":
			image = new Image(chemin + "boisIcone" + extention);
			break;
		case "PlanteMedicinal":
			image = new Image(chemin + "planteMedicinaleIcone" + extention);
			break;
		case "PlanteHercule":
			image = new Image(chemin + "planteHerculeIcone" + extention);
			break;
		case "PlanteDeNike":
			image = new Image(chemin + "planteNikéIcone" + extention);
			break;
		
		//chargement outils
		case "Hache":
			image = new Image(chemin + "Hache" + extention);
			Pane Case = (Pane) inv.getChildren().get(compteur);
			((Label)(Case.getChildren().get(1))).setText("1");
			break;
		case "Pioche":
			image = new Image(chemin + "Pioche" + extention);
			Pane tuile = (Pane) inv.getChildren().get(compteur);
			((Label)(tuile.getChildren().get(1))).setText("1");
			break;
		case "Pelle":
			image = new Image(chemin + "Pelle" + extention);
			Pane Tuile = (Pane) inv.getChildren().get(compteur);
			((Label)(Tuile.getChildren().get(1))).setText("1");
			break;
		case "Seau":
			image = new Image(chemin + "seau_vide" + extention);
			break;
		
		//chargement Potion
		case "PotionDegat":
			image = new Image(chemin + "potionDegat" + extention);
			break;
		case "PotionVie":
			image = new Image(chemin + "potionVie" + extention);
			break;
		case "PotionVitesse":
			image = new Image(chemin + "potionVitesse" + extention);
			break;
		case "AntiPoison":
			image = new Image(chemin + "potionAntipoison" + extention);
			break;
		
		//Chargement Arme
		case "Epee":
			image = new Image(chemin + "epee" + extention);
			break;
		case "Arc":
			image = new Image (chemin + "arc" + extention);
			break;
		case "Fleche":
			image = new Image(chemin + "fleche" + extention);
			break;
		default:
			break;
		}
		return image;
	}

}
