package application.modele;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import application.*;
import application.modele.exception.ErreurInventairePlein;

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
	
	public void ajouter(Item item) throws ErreurInventairePlein {// erreur à coder
		if(!estPleine()) {
			this.items.add(item);
		}
		else
			throw new ErreurInventairePlein();
	}
	
	public void supprimer(Item item) {
		this.items.remove(item);
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