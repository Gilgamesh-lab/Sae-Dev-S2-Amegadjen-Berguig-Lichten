package application.modele;

import application.modele.ressources.Ressource;

/**
 * Interface qui est au-dessus de Ressource, Potion, Outil, Arme 
 * @author jberguig
 *
 */
public interface  Item {
	
	public Ressource utiliser(int val);
	
	
}