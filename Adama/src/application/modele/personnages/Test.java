package application.modele.personnages;

import java.io.IOException;

import application.modele.Environnement;
import application.modele.exception.TailleMapException;

public class Test {

	public static void main(String[] args) throws TailleMapException, IOException {
		Environnement env = new Environnement();
		Joueur perso  = new Joueur(320, 0, env);
		Slime monstre = new Slime(320,11,1, env);
		//System.out.println(monstre.estSurLeJoueur(0));

	}

}
