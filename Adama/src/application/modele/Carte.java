package application.modele;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Carte {
	
	private Scanner map;
	private final static int HAUTEUR = 32;
	private final static int LARGEUR = 60;
	private ArrayList<Ressource> blockMap;

//	public Carte(int[][] carte) {
//		this.map = carte;
//		
//	}
//	
	public Carte() throws FileNotFoundException {
		this.map = Csv.ouvrir("exemple.csv");
		this.map.useDelimiter(",");
		this.blockMap = new ArrayList<Ressource>();
		this.blockMap.addAll(blockMap);
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
	
	public ArrayList<Ressource> creerBlock(){
		while(this.map.hasNext())
			if(this.map.next("\n") == null) {
				
			}
		return blockMap;
				
	}

}