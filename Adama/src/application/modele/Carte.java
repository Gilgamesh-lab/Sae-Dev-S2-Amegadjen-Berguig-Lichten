package application.modele;



import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Carte {

	private Scanner map;
	private final static int HAUTEUR = 32;
	private final static int LARGEUR = 60;


//	public Carte(int[][] carte) {
//		this.map = carte;
//		
//	}
//	
	public Carte() throws FileNotFoundException {
		this.map = Csv.ouvrir("exemple.csv");
		this.map.useDelimiter(",");
//		this.map = this.grille();

	}

	public Scanner getMap(){
		return this.map;
	}

	public int getHauteur() {
		return this.HAUTEUR;
	}

	public int getLargeur() {
		return this.LARGEUR;
	}
}