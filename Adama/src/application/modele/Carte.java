package application.modele;
import application.modele.exception.TailleMapException;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import application.modele.exception.ErreurInventairePlein;
import application.modele.exception.ErreurObjetIntrouvable;


public class Carte {
	private BufferedReader map;
	private final static int HAUTEUR = 32;
	private final static int LARGEUR = 60;
	private ArrayList<Ressource> blockMap; // 
	private Inventaire poubelle;
	 
	public Carte(String nomFichier) throws IOException, TailleMapException, ErreurInventairePlein {
		this.map = Csv.ouvrir(nomFichier);
		this.blockMap = new ArrayList<Ressource>(99);
		this.poubelle = new Inventaire(99);
		this.creerListeBlock();
	}
	
	public BufferedReader getMap(){
		return this.map;
	} 
	
	public int getHauteur() {
		return Carte.HAUTEUR;
	}
	
	public int getLargeur() {
		return Carte.LARGEUR;
	}
	
	public ArrayList<Ressource> getInventaire() {
		return this.blockMap;
	}
	
	public void creerListeBlock() throws TailleMapException, IOException, ErreurInventairePlein{
		String ligne;
		char suivant;
		int x = 0;
		int y = 0;
		ligne = this.map.readLine();
		while(ligne!=null) {
			for (int indice=0; indice<ligne.length()-1; indice++) {
				suivant=ligne.charAt(indice);
				switch (suivant) {
					case '1':
						blockMap.add(new Terre(x, y));
						break;
					case '2':
						blockMap.add(new Terre(x, y));
						break;
					case '3':
						blockMap.add(new Pierre(x, y, false));
						break;
					case ',':
						x++;
						break;
					default://tous las chiffres de tuile avec lesquelles on ne peut intéragir (ciel, nuage,...)
						blockMap.add(null);
						break;
				}	
			}
			x++;
			if (x!=LARGEUR)
				throw new TailleMapException("Problème de Largeur : "+x+" a la place des "+LARGEUR+" demandés.");
			x=0;
			y++;
			ligne = this.map.readLine();
		}
		if(y!=HAUTEUR)
			throw new TailleMapException("Problème de Hauteur : "+y+" a la place des "+HAUTEUR+" demandés.");
	}
	
	
	public void detruireBlock(Ressource ressource, int indice) throws ErreurInventairePlein, ErreurObjetIntrouvable {
		this.blockMap.remove(indice);
		this.poubelle.ajouter(ressource);
	}
	
	public boolean enDehorsMap(Ressource ressource) {
		return ressource.getX() < 0 || ressource.getY() > 0;
	}
	
	public void ressourceEnDehorsMap() throws ErreurInventairePlein, ErreurObjetIntrouvable {
		for(int i = 0 ; i < this.getInventaire().size(); i++) {
			if(this.enDehorsMap(this.getInventaire().get(i))){
				this.detruireBlock(this.getInventaire().get(i), i);
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
