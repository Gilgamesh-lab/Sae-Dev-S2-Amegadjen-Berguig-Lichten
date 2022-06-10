package application.modele.outils;

import application.modele.Environnement;
import application.modele.ressources.Eau;

public class Seau extends Outil {
	
	private boolean estRempli;
	private final static int TEMPS_REMPLISSAGE = 200;
	private Eau eau;

	public Seau(Environnement env) {
		super(env, TEMPS_REMPLISSAGE);
		this.estRempli = false;
		eau = null;
	}

	@Override
	public void utiliser(int val) {
		// TODO Auto-generated method stub

	}
	
	public void remplir() {
		eau = new Eau();
		estRempli = true;
	}
	
	public void vider() {
		eau = null;
		estRempli = false;
	}
	
	public boolean EstRempli() {
		return estRempli;
	}

	public static int getTempsRemplissage() {
		return TEMPS_REMPLISSAGE;
	}

	public Eau getEau() {
		return eau;
	}
	
	
	
	
}
