package application.modele;
import java.io.*;
import java.nio.file.Files; 

import java.nio.file.Paths;



public class Csv {
    
    /**
     * Renvoie le chemin absolue d'un fichier
     * @param nom : nom du fichier avec son extension
     * @return le chemin absolue du fichier passer 
     */
    public static String getChemin(String nom) {
     	File getCSVFiles = new File("");
     	return getCSVFiles.getAbsolutePath() + "/src/ressource/" + nom ;
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