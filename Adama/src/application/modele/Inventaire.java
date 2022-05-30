package application.modele;

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
	
	public ObservableList<Item> getItems() {
		return this.items;
	}
	
	public Item getItem(int indice) {
		return this.items.get(indice);
	}
	
	public final int getTailleMax() {
		return this.tailleMaxProperty.getValue();
	}
	
	public final void setTailleMax(int val) {
		this.tailleMaxProperty.setValue(val);
	}
	
	public final IntegerProperty tailleMaxProperty() {
		return this.tailleMaxProperty;
	}
	
	public int getTaille() {
		return this.items.size();
	}
	
	public void ajouter(Item item) {// erreur à coder
		if(!estPleine()) {
			this.items.add(item);
		}
	}
	
	public void supprimer(Item item) {
		this.items.remove(item);
	}
	
	public void supprimer(int indice) {
		this.items.remove(indice);
	}
	
	public void remplacer(Item item, int indice) {
		this.items.add(indice, item);
	}
	
	public void supprimerAvecIndice(int indice) {
		this.items.remove(indice);
	}
	
	public boolean estPleine() {
		return this.items.size() == this.getTaille();
	}
}