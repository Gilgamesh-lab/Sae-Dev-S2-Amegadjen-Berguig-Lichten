package application.modele;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


public class Csv {
	
    public static Scanner ouvrir2(String nom) throws FileNotFoundException {
    	FileReader text = new FileReader(nom);
    	Scanner sc = new Scanner(text);
    	return sc;
    }
    
    public static String getChemin(String nom) {
     	File getCSVFiles = new File("");
     	return getCSVFiles.getAbsolutePath() + "/src/ressource/" + nom ;
     }

    
    public static int[][] csvToTab(String nom, int hauteur, int largeur) throws IOException {
    	Scanner sc = ouvrir2(getChemin(nom));
    	sc.useDelimiter(",");
    	int i = 0;
    	int k = 0 ;
    	int[][] tab = new int[hauteur][largeur];
    	try {
    		if(!sc.hasNext()) {
    			return tab;
    		}
    		
	    	for(i = 0 ; i < tab.length   ; i++) {
	    		for(k = 0; k < tab[0].length  ; k++) {
	    			tab[i][k] = Integer.parseInt(sc.next());
	    			System.out.print(tab[i][k]);
	    		}
	    		System.out.println(" | " + i );
	    		System.out.println("");
	    		sc.nextLine();
	    	}
    	}catch(Exception e) {
    		System.out.println("Errreur : " );
			System.out.println("");
			e.printStackTrace();
		}
    	
    	return tab; 
    }
    
    public static int[][] tableau(Scanner sc) {
		int[][] tab = new int[28][60];
		int y = 0;
		int x = 0;
		sc.useDelimiter(",");
		 
		while (sc.hasNext())
        { 
			try {
				tab[y][x] = Integer.parseInt(sc.next()) ; 
	            x ++;
			}catch(java.lang.NumberFormatException e) {
            	sc.nextLine();
            	x  = 0;
            	y++;
            }
        }
		
		return tab;
	}
    
    public static void lectureTab(int[][] tab2) {
    	for(int i = 0; i< tab2.length  ; i++) {
    		for(int k = 0; k < tab2[0].length  ; k++) {
    			System.out.print(tab2[i][k] );
    		}
    		System.out.print( " | " + i);
    		System.out.println("");
    	}
    }
     
     public static  BufferedReader ouvrir (String nom) throws IOException{
    	 File file = new File(getChemin(nom));
    	 
         Reader reader = new FileReader(file);
         BufferedReader br = new BufferedReader(reader);
         return br;
         
     }
    
	public static int[][] readCSV(String nomFichier, char c, Charset charset) throws IOException {
		return Files.lines(Paths.get(getChemin(nomFichier)), charset)
				.map(ligne-> ligne.split(String.valueOf(c)))
			    .toArray(int[][]::new);
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