import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environnement {
	private ObservableList<Personnage> personnages;
	private Carte carte;
	
	
	
	public Environnement(Carte carte) {
		this.carte = carte;
		this.personnages = FXCollections.observableArrayList();
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
	
	
}
