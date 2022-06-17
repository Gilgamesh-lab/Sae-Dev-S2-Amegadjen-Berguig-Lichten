package application;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import application.modele.Carte;
import application.modele.Environnement;
import application.modele.exception.ErreurInventairePlein;
import application.modele.exception.ErreurObjetIntrouvable;
import application.modele.exception.TailleMapException;
import application.modele.personnages.Joueur;
import application.modele.personnages.Slime;

class PersonnageTest {

	@Test
	public final void testEstMort() throws TailleMapException, IOException {
		Environnement env = new Environnement();
		Joueur joueur = new Joueur(5,5,env); 
		while(joueur.getPv() > 0) {
			assertFalse(joueur.estMort());
			joueur.degat();
		}
		assertTrue(joueur.estMort());
	}
	
	
	@Test
	public final void testDecrementerPv() throws TailleMapException, IOException, ErreurInventairePlein {
		Environnement env = new Environnement(); // cas on retire une partie de ses pv à un joueur
		Joueur joueur = new Joueur(5,5,env);
		joueur.decrementerPv(6);
		
		assertEquals(1,joueur.getPv());
		
		joueur.setPv(7); // cas on retire tout les pv du joueur
		joueur.decrementerPv(7);
		assertEquals(0,joueur.getPv());
	
		joueur.setPv(7); // cas on retire à un joueur plus de pv qu'il en possède 
		joueur.decrementerPv(10);
		assertEquals(0,joueur.getPv());
	}
	
	
	@Test
	public final void testEstEnDehorsMap() throws TailleMapException, IOException {
		Environnement env = new Environnement();
		Joueur joueur = new Joueur(5,5,env);
		
		assertFalse(joueur.estEnDehorsMap(0)); // x se trouve dans la map
		
		joueur.setX(-5);  
		assertTrue(joueur.estEnDehorsMap(0)); // cas x négatif
		
		joueur.setX(2000);
		assertTrue(joueur.estEnDehorsMap(0)); // cas x en dehors de la map
		
		joueur.setX(0);
		assertTrue(joueur.estEnDehorsMap(0)); // cas x à la limite droite de la map
		
		joueur.setX(Carte.TAILLE_BLOCK * Carte.LARGEUR);
		assertTrue(joueur.estEnDehorsMap(0)); // cas x à la limite gauche de la map
	}
	
	
	@Test
	public final void testOuSeTrouveLeJoueur() throws TailleMapException, IOException, ErreurObjetIntrouvable {
		Environnement env = new Environnement();
		Joueur joueur = new Joueur(5,5,env);
		Slime slime  = new Slime(3,5, env);
		
		assertTrue(slime.ouSeTrouveLeJoueur()); // cas le joueur se trouve à droite du monstre 
		
		slime.setX(7);
		assertFalse(slime.ouSeTrouveLeJoueur()); // cas le joueur se trouve à gauche du monstre 
		
		slime.setX(5);
		assertFalse(slime.ouSeTrouveLeJoueur()); // cas le joueur sur le monstre monstre 
	}
	
	@Test
	public final void testEstPrèsDuJoueur() throws TailleMapException, IOException, ErreurObjetIntrouvable {
		Environnement env = new Environnement();
		Joueur joueur = new Joueur(5,5,env);
		Slime slime  = new Slime(7,5, env);
		
		/* cas x */ 
		
		assertTrue(slime.estPrèsDuJoueur(3, 0)); // cas le joueur se trouve dans le rayon du slime, à sa gauche
		
		assertFalse(slime.estPrèsDuJoueur(1, 0)); // cas le joueur ne se trouve pas dans le rayon du slime, à sa gauche
		
		assertTrue(slime.estPrèsDuJoueur(2, 0)); // cas le joueur  se trouve à la limite du rayon du slime, à sa gauche
		
		slime.setX(3);
		assertTrue(slime.estPrèsDuJoueur(3, 0)); // cas le joueur se trouve dans le rayon du slime, à sa droite
		
		assertFalse(slime.estPrèsDuJoueur(1, 0)); // cas le joueur ne se trouve pas dans le rayon du slime, à sa droite
		
		assertTrue(slime.estPrèsDuJoueur(2, 0)); // cas le joueur  se trouve à la limite du rayon du slime,  à sa droite
		
		/* cas y */ 
		
		slime.setX(5);
		slime.setY(7);
		assertTrue(slime.estPrèsDuJoueur(0, 3)); // cas le joueur se trouve dans le rayon du slime, au dessus du slime
		
		assertFalse(slime.estPrèsDuJoueur(0, 1)); // cas le joueur ne se trouve pas dans le rayon du slime, au dessus du slime
		
		assertTrue(slime.estPrèsDuJoueur(0, 2)); // cas le joueur  se trouve à la limite du rayon du slime, au dessus du slime
		
		/* cas x et y */ 
		
		slime.setX(7);
		slime.setY(7);
		assertTrue(slime.estPrèsDuJoueur(3, 3)); // cas le joueur se trouve dans le rayon du slime, en dessous du slime et à sa droite
		
		assertFalse(slime.estPrèsDuJoueur(1, 1)); // cas le joueur ne se trouve pas dans le rayon du slime, en dessous du slime et à sa droite
		
		assertTrue(slime.estPrèsDuJoueur(2, 2)); // cas le joueur  se trouve à la limite du rayon du slime, en dessous du slime et à sa droite
		
		
		/* cas égalité */ 
		
		slime.setX(5);
		slime.setY(5);
		
		assertTrue(slime.estPrèsDuJoueur(3, 3)); // cas le joueur se trouve sur le slime, avec un rayon de 3,3
		
		assertTrue(slime.estPrèsDuJoueur(0, 0)); // cas le joueur se trouve sur le slime, avec un rayon null de 0,0
		
		/* cas négatif */ 
		
		slime.setX(7);
		slime.setY(7);
		
		assertFalse(slime.estPrèsDuJoueur(-3, 0)); // cas le rayon à une valeur négatif pour le x
		
		assertFalse(slime.estPrèsDuJoueur(0, -3)); // cas le rayon à une valeur négatif pour le y
		
		assertFalse(slime.estPrèsDuJoueur(-3, -3)); // cas le rayon à une valeur négatif pour le x et le y
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
