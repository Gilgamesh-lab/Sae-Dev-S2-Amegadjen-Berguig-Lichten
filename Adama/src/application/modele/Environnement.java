package application.modele;
import java.io.FileNotFoundException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environnement {
	private ObservableList<Personnage> personnages;
	private ObservableList<Item> items;
	private Carte carte;



	public Environnement(Carte carte) {
		this.carte = carte;
		this.personnages = FXCollections.observableArrayList();
		this.items = FXCollections.observableArrayList();
	}

	public Environnement() throws FileNotFoundException {
		this.carte = new Carte();
		this.personnages = FXCollections.observableArrayList();
	}

	/*public Environnement() {
		int tab[][] = new int[32][60];
		this.carte = new Carte(tab);
		this.personnages = FXCollections.observableArrayList();
	}*/

	public void getItems() {

	}

	public void ajouter(Item item) {
		this.items.add(item);
	}

	public void ajouter(Personnage personnage) {
		this.personnages.add(personnage);
	}
	
	public void supprimer(Personnage personnage) {
		this.personnages.remove(personnage);
	}
	
	public void supprimer(int indice) {
		this.personnages.remove(indice);
	}
	
	
	public Personnage getPersonnage(int indice) {
		return this.personnages.get(indice);
	}
	
	public ObservableList<Personnage> getPersonnages(){
		return this.personnages;
	}

	public Carte getCarte(){
		return this.carte;
	}
}