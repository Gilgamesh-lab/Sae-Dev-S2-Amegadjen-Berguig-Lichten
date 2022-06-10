package application.modele;

import application.modele.exception.ErreurInventairePlein;
import application.modele.personnages.Joueur;

/**
 * Interface qui est au-dessus de Ressource, Potion, Outil, Arme 
 * @author jberguig
 *
 */
public interface  Item {
	
	public void utiliser(int val, Joueur joueur) throws ErreurInventairePlein;
	
	
}