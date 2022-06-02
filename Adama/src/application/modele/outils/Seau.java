package application.modele.outils;

import application.modele.Environnement;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Seau extends Outil {
	
	private BooleanProperty estRempli;
	private final static int TEMPS_REMPLISSAGE = 200;

	public Seau(Environnement env) {
		super(env, TEMPS_REMPLISSAGE);
		this.estRempli = new SimpleBooleanProperty(false);
	}

	@Override
	public void utiliser(int val) {
		// TODO Auto-generated method stub

	}

}
