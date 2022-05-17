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
	
	public void echanger(Item item1, int indice1, Item item2, int indice2) {// échanger  deux objets déjà dans l'inventaire
		this.remplacer(item1, indice2);
		this.remplacer(item2, indice1);
	}
	
	public boolean estPlein() {
		return this.getTaille() >= this.getTailleMax();
	}
	
	
	public void transfererInventaire(Inventaire inventaireSource) throws ErreurInventairePlein {
		for (int i = 0 ; i < inventaireSource.getTaille() ; i++) {
			this.ajouter(inventaireSource.getItem(i));
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
