package application.modele;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import application.modele.Ressources.Bois;
import application.modele.Ressources.Pierre;
import application.modele.Ressources.Ressource;
import application.modele.Ressources.Terre;
import application.modele.exception.ErreurInventairePlein;
import application.modele.exception.ErreurObjetIntrouvable;
import application.modele.exception.TailleMapException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Carte {

	private BufferedReader map;
	private final static int HAUTEUR = 32;
	private final static int LARGEUR = 60;
	private final static int TAILLE_BLOCK = 32;
	private ObservableList<Ressource> blockMap;
	private ArrayList<Item> items;

	public Carte() throws TailleMapException, IOException {
		this.map = Csv.ouvrir("mapsTest.csv");
		this.blockMap = FXCollections.observableArrayList();
		creerListeBlock();
		this.items = new ArrayList<Item>();
	}

	public int getHauteur() {
		return HAUTEUR;
	}

	public int getLargeur() {
		return LARGEUR;
	}

	public ObservableList<Ressource> getBlockMap() {
		return blockMap;
	}

	public static int getTailleBlock() {
		return TAILLE_BLOCK;
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Ressource emplacement(int x, int y) {
		int indiceDansMap = (x/TAILLE_BLOCK) + ((y/TAILLE_BLOCK) * LARGEUR);
		return this.blockMap.get(indiceDansMap);
	}
	
	public Ressource emplacement(int indice) {
		return this.blockMap.get(indice);
	}

	/**
	 * 
	 * @throws TailleMapException
	 * @throws IOException
	 */
	public void creerListeBlock() throws TailleMapException, IOException{
		String ligne;
		char suivant;
		int x = 0;
		int y = 0;
		ligne = this.map.readLine();
		//System.out.println(ligne.length());
		while(ligne!=null) {
			for (int indice=0; indice<ligne.length(); indice+=2) {
				suivant=ligne.charAt(indice);
				switch (suivant) {
					case '3':
						blockMap.add(new Terre(true, x*TAILLE_BLOCK, y*TAILLE_BLOCK, x+(y*((ligne.length()+1)/2))));
						break;
					case '4':
						blockMap.add(new Terre(true, x*TAILLE_BLOCK, y*TAILLE_BLOCK, x+(y*((ligne.length()+1)/2))));
						break;
					case '5':
						blockMap.add(new Pierre(false, x*TAILLE_BLOCK, y*TAILLE_BLOCK, x+(y*((ligne.length()+1)/2))));
						break;
					case '6':
						blockMap.add(new Bois(false, x*TAILLE_BLOCK, y*TAILLE_BLOCK, x+(y*((ligne.length()+1)/2))));
						break;
//					case ',':
//						x++;
//						break;
					default://tous las chiffres de tuile avec lesquelles on ne peut intéragir (ciel, nuage,...)
						blockMap.add(null);
						break;
				}	
				x++;
			}
			if (x!=LARGEUR)
				throw new TailleMapException("Problème de Largeur : "+x+" a la place des "+LARGEUR+" demandés.");
			x=0;
			y++;
			ligne = this.map.readLine();
		}
		if(y!=HAUTEUR)
			throw new TailleMapException("Problème de Hauteur : "+y+" a la place des "+HAUTEUR+" demandés.");
//		System.out.println(blockMap.size());
	}
	
//	public void detruireBlock(int indice) throws ErreurInventairePlein, ErreurObjetIntrouvable {
//		this.poubelle.ajouter(this.blockMap.remove(indice));
//		this.blockMap.add(indice, null);
//	}

	public void detruireBlock(int indice) {
		this.items.add(this.blockMap.remove(indice));
		this.blockMap.add(indice, null);
	}
	
	public boolean enDehorsMap(Ressource ressource) {
		return ressource.getX() < 0 || ressource.getY() > 0;
	}

	/**
	 * Si on trouve des blocs dans blockMap avec x et y en dehors de la map ils sont détruit.
	 */
	public void ressourceEnDehorsMap() {
		for(int i = 0 ; i < this.getBlockMap().size(); i++) {
			if(this.enDehorsMap(this.getBlockMap().get(i))){
				this.detruireBlock(i);
			}
		}
	}

	public void attaquerBlock(int indice, int val) {
		this.blockMap.get(indice).prendreDegat(val);
		if (this.blockMap.get(indice).estDetruit())
			detruireBlock(indice);			
	}

	public ArrayList<Item> getItems() {
		return items;
	}
	
}

