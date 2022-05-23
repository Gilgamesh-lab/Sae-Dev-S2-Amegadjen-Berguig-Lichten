package application.modele;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import application.modele.exception.TailleMapException;

public class Carte {

	private BufferedReader map;
	private final static int HAUTEUR = 32;
	private final static int LARGEUR = 60;
	private ArrayList<Ressource> blockMap;

	public Carte() throws TailleMapException, IOException {
		this.map = Csv.ouvrir("exemple.csv");
		this.blockMap = new ArrayList<Ressource>();
		creerListeBlock();
	}

	public BufferedReader getMap(){
		System.out.println(map);
		return this.map;
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

	public void creerListeBlock() throws TailleMapException, IOException{
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
						blockMap.add(new Terre(x, y, false));
						break;
					case '2':
						blockMap.add(new Terre(x, y, false));
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
}