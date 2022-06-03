package application.modele;

import java.io.IOException;

import application.modele.exception.ErreurObjetIntrouvable;
import application.modele.exception.TailleMapException;
import application.modele.personnages.Ennemis;
import application.modele.personnages.Joueur;
import application.modele.personnages.Loup;

public class Lancement {

	public static void main(String[] args) throws TailleMapException, IOException, ErreurObjetIntrouvable {
		Carte carte =  new Carte ();
		Environnement env = new Environnement(carte);
		Joueur joueur = new Joueur(10,5,env);
		int[] taille = new int[5];
		taille[0] = 1;
		taille[1] = 1;
		
		Ennemis loup = new Loup(env,taille, 8,false);
		loup.setX(5);
		System.out.println(loup.ouSeTrouveLeJoueur());
		System.out.println(loup.estAporterDuJoueur());
	}

}
