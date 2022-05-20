package application.modele;
import java.io.*;
import java.util.Scanner;


public class Csv {

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

    public static void lecture(Scanner sc) {
    	sc.useDelimiter(",");
        while (sc.hasNext())
        {
            System.out.print(sc.next() + " | ");
        }
        sc.close();
    }
    
}