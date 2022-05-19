package application.modele;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


public class Csv {
	
    public static void main(String[] args) throws IOException {// faire méthode écriture
    	/*Scanner sc = ouvrir("exemple.csv");
    	sc.useDelimiter(",");
    	int[][] tab = new int[32][60];
    	for(int i = 0; i < 32 ; i++) {
    		try {
	    		for(int k = 0 ; k < 60; k++) {
	    			tab[i][k] = Integer.parseInt(sc.next());
	    			sc.next();
	    		}
    		}catch(Exception e) {
    			System.out.println("Errreur");
    			sc.next();
    		}
    		sc.nextLine();

    	}
    	*/
    	
    	Environnement env = new Environnement();
    	Joueur joueur = new Joueur(5,48,13,env); // 45 , 14
    	
    	System.out.println(joueur.estSurDeLaTerre());
    	
        
        
    }
    
    public static Scanner ouvrir(String nom) throws FileNotFoundException {
    	try { // chemin relatif
    		File getCSVFiles = new File(nom);
    		Scanner sc = new Scanner(getCSVFiles);
    		return sc;
    		
    	}catch(FileNotFoundException e) { // chemin absolue
    		File getCSVFiles = new File(""); 
    		String chemin = getCSVFiles.getAbsolutePath();
            getCSVFiles = new File(chemin + "/src/application/modele/" + nom);
            Scanner sc = new Scanner(getCSVFiles);
            return sc;
       }
    }
    
    public static String getChemin(String nom) {
    	File getCSVFiles = new File("");
    	return getCSVFiles.getAbsolutePath() + "/src/application/modele/" + nom ;
    }
    
    
    public static int[][] csvToTab(String nom, int hauteur, int largeur) throws IOException {
    	String[][] tab = readCSV(getChemin(nom), ',', StandardCharsets.UTF_8);
    	
    	int[][] tab2 = new int[tab.length][tab[0].length];
    	try {
	    	for(int i = 0; i< tab2.length -2  ; i++) {
	    		for(int k = 0; k < tab2[0].length  -2; k++) {
	    			tab2[i][k] = Integer.parseInt(tab[i][k]);
	    			
	    			System.out.print(tab2[i][k]);
	    		}
	    		System.out.println(" | " + i );
	    		System.out.println("");
	    	}
    	}catch(Exception e) {
			System.out.println("Errreur");
			System.out.println("");
		}
    	
    	return tab2;
    }
    
    public static String[][] readCSV(String nomFichier, char c, Charset charset) throws IOException {
		return Files.lines(Paths.get(nomFichier), charset)
			     .map(ligne-> ligne.split(String.valueOf(c))).toArray(String[][]::new);
	}
    
    public static void lecture(Scanner sc) {
    	sc.useDelimiter(",");
        while (sc.hasNext())
        {
            System.out.print(sc.next() + " | "); 
        }
        sc.close();
    }
}
