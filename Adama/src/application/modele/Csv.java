package application.modele;
import java.io.*;
import java.nio.file.Files; 

import java.nio.file.Paths;



public class Csv {
	
    public static void main(String[] args) throws IOException {// faire méthode écriture

    	File repertoire = new File(getChemin(""));
        String liste[] = repertoire.list();      
 
        if (liste != null) {         
            for (int i = 0; i < liste.length; i++) {
                System.out.println(liste[i]);
            }
        } else {
            System.err.println("Nom de repertoire invalide");
        } 
 
    }
    
    
     /**
      * Méthode qui écrit dans un fichier du texte 
      * @param nomFichier : nom du fichier avec son extension
      * @param texte : texte à écrire dans le fichier nouvellement crée
      * @throws IOException
      */
     public static void ecrire(String nomFichier, String texte) throws IOException {// si sauvegarde
    	FileWriter file = new FileWriter(nomFichier);
    	file.write(texte);
    	Files.move(Paths.get(getCheminAdama(nomFichier)) ,Paths.get(getChemin(nomFichier)));
    	file.close();
    }

    /**
     *  Renvoie le chemin absolue d'un fichier présent dans le répertoire Adama
     * @param nom : nom du fichier dans le répertoire Adama
     * @return  Renvoie le chemin absolue du fichier
     */
    public static String getCheminAdama(String nom) {
     	File getCSVFiles = new File("");
     	return getCSVFiles.getAbsolutePath() + "/" + nom ;
     }
    
    /**
     * Renvoie le chemin absolue d'un fichier
     * @param nom : nom du fichier avec son extension
     * @return le chemin absolue du fichier passer 
     */
    public static String getChemin(String nom) {
     	File getCSVFiles = new File("");
     	return getCSVFiles.getAbsolutePath() + "/src/application/modele/" + nom ;
     }
    


    /**
     * renvoie  le texte présent dans un fichier 
     * @param nom : nom du fichier avec son extension
     * @return  renvoie un scanner contenant le texte présent dans un fichier 
     * @throws IOException
     */
    public static  BufferedReader ouvrir (String nom) throws IOException{
	   	File file = new File(getChemin(nom));
	   	Reader reader = new FileReader(file);
	    BufferedReader br = new BufferedReader(reader);
	    return br;
        
    }
    
    
    


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}