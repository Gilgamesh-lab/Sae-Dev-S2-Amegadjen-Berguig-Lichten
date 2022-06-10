package application.modele.personnages;

import application.modele.ressources.Ressource;

public class Fils extends Ressource {

	public Fils(int indice) {
		super(false, 0, -1, -1, -1, indice);
	}

	@Override
	public Ressource utiliser(int val) {
		return null;

	}

	@Override
	public void utiliser() {

	}

}
