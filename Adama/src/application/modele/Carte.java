package application.modele;

public class Carte {
	private int[][] carte;
	private final static int HAUTEUR = 32;
	private final static int LARGEUR = 60;
	
	
	public Carte() {
		this.carte = this.grille();
		
	}
	
	public int[][] getCarteTab(){
		return this.carte;
	}
	
	public int[][] grille() {
		int[][] tab16l9 = new int[LARGEUR][HAUTEUR];
		for (int i=0; i<LARGEUR; i++)
			for(int j=0; j<HAUTEUR; j++)
				if (i<30)
					tab16l9[i][j] = 0;
				else if (tab16l9[i-1][j]==0 || (tab16l9[i-2][j]==0 && j<28))
					tab16l9[i][j] = 2;
				else
					tab16l9[i][j] = 1;
//		affichergrille(tab16l9);
		return tab16l9;
	}

	private static void affichergrille(int[][] tab) {
		System.out.println("[");
		for (int i=0; i<LARGEUR; i++) {
			System.out.print("[");
			for(int j=0; j<HAUTEUR-1; j++)
				System.out.print(tab[i][j]+",");
			System.out.println(tab[i][HAUTEUR-1]+"]");
		}
		System.out.println("]");
	}	
}

