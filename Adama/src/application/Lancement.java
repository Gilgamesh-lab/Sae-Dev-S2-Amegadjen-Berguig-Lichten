package application;

import application.modele.Inventaire;
import application.modele.armes.Poing;
import application.modele.exception.ErreurInventairePlein;

public class Lancement {

	public static void main(String[] args) throws ErreurInventairePlein {
		Inventaire  inventaire = new Inventaire(1);
		Inventaire  inventaire2 = new Inventaire(5);
		Poing main =  new Poing();
		
		inventaire.supprimer(main);
		inventaire.transferer(main, inventaire2);

	}

}
