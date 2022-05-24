package application.modele;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import application.modele.exception.ErreurInventairePlein;
import application.modele.exception.ErreurObjetIntrouvable;
import application.modele.exception.TailleMapException;

public class Carte {

	private BufferedReader map;
	private final static int HAUTEUR = 32;
	private final static int LARGEUR = 60;
	private final static int TAILLE_BLOCK = 32;
	private ArrayList<Ressource> blockMap;
	private Inventaire poubelle;
	private ArrayList<Item> items;

	public Carte() throws TailleMapException, IOException {
		this.map = Csv.ouvrir("testMap.csv");
		this.blockMap = new ArrayList<Ressource>();
		this.poubelle = new Inventaire(99);
		creerListeBlock();
	}

	public int getHauteur() {
		return HAUTEUR;
	}

	public int getLargeur() {
		return LARGEUR;
	}

	public ArrayList<Ressource> getBlockMap() {
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
			for (int indice=0; indice<ligne.length(); indice=indice+2) {
				suivant=ligne.charAt(indice);
				switch (suivant) {
					case '1':
						blockMap.add(new Terre(x*TAILLE_BLOCK, y*TAILLE_BLOCK, false));
						break;
					case '2':
						blockMap.add(new Terre(x*TAILLE_BLOCK, y*TAILLE_BLOCK, false));
						break;
					case '3':
						blockMap.add(new Pierre(x*TAILLE_BLOCK, y*TAILLE_BLOCK, false));
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
	 * Si on touve blocs dans liste de la map avec x et y endors de la map ils sont détruit.
	 * @throws ErreurInventairePlein
	 * @throws ErreurObjetIntrouvable
	 */
	public void ressourceEnDehorsMap() throws ErreurInventairePlein, ErreurObjetIntrouvable {
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
}

