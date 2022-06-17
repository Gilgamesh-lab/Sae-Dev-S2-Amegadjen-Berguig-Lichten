package application.modele;

import java.util.ArrayList;

import application.modele.armes.Fleche;
import application.modele.exception.ErreurInventairePlein;
import application.modele.exception.ErreurObjetIntrouvable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventaire {
	private ObservableList<Item> items;
	private IntegerProperty tailleMaxProperty;


	public Inventaire(int taille) {
		this.items = FXCollections.observableArrayList();
		this.tailleMaxProperty = new SimpleIntegerProperty(taille);
	}
	
	public Inventaire(Item item) {
		this.items = FXCollections.observableArrayList();
		this.tailleMaxProperty = new SimpleIntegerProperty(1);
		this.items.add(item);
	}

	public ObservableList<Item> getItems() {
		return this.items;
	}

	public Item getItem(int indice) {
		return this.items.get(indice);
	}

	public void setItems(ObservableList<Item> items) {
		this.items = items;
	}

	public final int getTailleMax() {
		return this.tailleMaxProperty.getValue();
	}

	public final IntegerProperty tailleMaxProperty() {
		return this.tailleMaxProperty;
	}

	public int getTaille() {
		return this.items.size();
	}

	public void ajouter(Item item) throws ErreurInventairePlein {
		if(!estPlein()) {
			this.items.add(item);
		}
		else {
			throw new ErreurInventairePlein();
		}
	}




	public void supprimer(Item item) {
		this.items.remove(item);
	}

	public void supprimer(int indice) {
		this.items.remove(indice);
	}

	public void remplacer(Item item, int indice) { // objet ramassé qui remplace un objet présent dans l'inventaire qui sera jeté
		this.items.add(indice, item);
	}

	public Item setItem(Item item, int indice) {
		return this.items.set(indice, item);
	}

	public void echanger(Item item1, int indice1, Item item2, int indice2) {// échanger  deux objets déjà dans l'inventaire
		this.remplacer(item1, indice2);
		this.remplacer(item2, indice1);
	}

	public boolean estPlein() {
		return this.getTaille() >= this.getTailleMax();
	}

	public void transferer(Item item, Inventaire inventaireSource) throws ErreurInventairePlein { // terminer inventaire, environnement(inventaire), ressources,faire boolean pause, saut
		this.ajouter(item);
		inventaireSource.supprimer(item); // indice plus précis mais je ne sais pas si je vais y avoir accès
	}

	public void ajouterInventaire(Inventaire inventaireSource) throws ErreurInventairePlein {
		for (int i = 0 ; i < inventaireSource.getTaille() ; i++) {
			this.transferer(inventaireSource.getItem(i), inventaireSource);

		}
	}

	
	









}
