

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environnement {
	private int[][] carte;
	private ObservableList<Personnage> personnages;
	
	public Environnement(int[][] carte) {
		this.carte = carte;
		this.personnages = FXCollections.observableArrayList();
	}
	
	public int[][] getCarte(){
		return this.carte;
	}
	
	public ObservableList<Personnage> getPersonnages(){
		return this.personnages;
	}
}
