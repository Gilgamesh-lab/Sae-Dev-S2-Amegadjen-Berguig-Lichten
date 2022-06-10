package application.modele.outils;

import application.modele.Environnement;
import application.modele.Item;
import application.modele.ressources.Ressource;

/**
 * Est au-dessus de Hache, Pelle, Pioche
 * @author jberguig
 *
 */
public abstract class Outil implements Item{
	
	private Environnement env;
	private int effet; //correspond au dégat infligé au bloc e
	
	/**
	 * Crée un outil (soit une hache, soit une pelle, soit une pioche
	 * @param env L'environnement dans lequel elle setrouve et avec lequel elle intéragie.
	 * @param effet est la valeur nécessaire à l'utilisation(le temps, les dégats, la vitesse,...)
	 */
	public Outil(Environnement env, int effet) {
		this.env=env;
		this.effet=effet;
	}
	
	@Override
	public abstract Ressource utiliser(int val);
	
	
	
	/**
	 * 
	 * @return l'environnement
	 */
	public Environnement getEnvironnement() {
		return env;
	}
}
