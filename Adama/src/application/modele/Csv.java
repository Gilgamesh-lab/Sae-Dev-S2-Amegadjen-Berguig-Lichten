package application.modele;
import java.io.*;
import java.nio.file.Files; 

import java.nio.file.Paths;
import java.util.Scanner;



public class Csv {
	
    public static void main(String[] args) throws IOException {// faire méthode écriture

    	ecrire("test.txt", "test"); 
 
    }
    
    
    
     public static void ecrire(String nomFichier, String texte) throws IOException {
    	FileWriter file = new FileWriter(nomFichier);
    	file.write(texte);
    	Files.move(Paths.get(getCheminAdama(nomFichier)) ,Paths.get(getChemin(nomFichier)));
    	file.close();
    }

    
    public static Scanner ouvrir2(String nom) throws FileNotFoundException {
    	FileReader text = new FileReader(nom);
    	Scanner sc = new Scanner(text);
    	return sc;
    		
   
    }
    
    public static String getCheminAdama(String nom) {
     	File getCSVFiles = new File("");
     	return getCSVFiles.getAbsolutePath() + "/" + nom ;
     }
    
    public static String getChemin(String nom) {
     	File getCSVFiles = new File("");
     	return getCSVFiles.getAbsolutePath() + "/src/application/modele/" + nom ;
     }
    

    
    public static Ressources[][] csvToTab(String nom, int hauteur, int largeur) throws IOException {
    	Scanner sc = ouvrir2(getChemin(nom));
    	sc.useDelimiter(",");
    	int i = 0;
    	int k = 0 ;
    	Ressources [][] tab = new Ressources[hauteur][largeur];
    	try {
    		if(!sc.hasNext()) {
    			return tab;
    		}
    		
	    	for(i = 0 ; i < tab.length   ; i++) {
	    		for(k = 0; k < tab[0].length  ; k++) {
	    			tab[i][k] = intToRessource(Integer.parseInt(sc.next()), k, i);
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
    
    public static Ressources intToRessource(int val, int x, int y) {
		Ressources bloc;
		switch(val) {
		case(1): 
			bloc = new Terre(x, y);
			break;
			
		default:
			bloc = null;
			break;
		}
		
		return bloc;
	}
    
    public static  BufferedReader ouvrir (String nom) throws IOException{
   	 File file = new File(getChemin(nom));
   	 Reader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);
        return br;
        
    }
    
    
    


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
