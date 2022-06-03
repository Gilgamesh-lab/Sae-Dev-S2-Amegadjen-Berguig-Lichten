package application.modele.potions;

import application.modele.Item;
import application.modele.personnages.Joueur;

public interface Potion extends Item {
	
	public void utiliser(Joueur joueur);

}
