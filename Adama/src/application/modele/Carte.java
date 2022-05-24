package application.modele;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import application.modele.exception.TailleMapException;

public class Carte {

	private BufferedReader map;
	private final static int HAUTEUR = 32;
	private final static int LARGEUR = 60;
	private final static int TAILLE_BLOCK = 32;
	private ArrayList<Ressource> blockMap;

	public Carte() throws TailleMapException, IOException {
		this.map = Csv.ouvrir("testMap.csv");
		this.blockMap = new ArrayList<Ressource>();
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
	 * @param taille
	 * @return
	 */
	public Ressource emplacement(int x, int y, int[] taille) {
		int indiceDansMap = (x/TAILLE_BLOCK) + ((y/TAILLE_BLOCK) * LARGEUR);
		return this.blockMap.get(indiceDansMap);
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
}
