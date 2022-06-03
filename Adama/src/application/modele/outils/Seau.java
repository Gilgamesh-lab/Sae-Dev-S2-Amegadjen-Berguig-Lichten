package application.modele.outils;

import application.modele.Environnement;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Seau extends Outil {
	
	private BooleanProperty estRempliProperty;
	private final static int TEMPS_REMPLISSAGE = 200;

	public Seau(Environnement env) {
		super(env, TEMPS_REMPLISSAGE);
		this.estRempliProperty = new SimpleBooleanProperty(false);
	}

	@Override
	public void utiliser(int val) {
		// TODO Auto-generated method stub

	}

	public BooleanProperty EstRempliProperty() {
		return estRempliProperty;
	}
	
	public boolean getEstEstRempli() {
		return estRempliProperty.get();
	}
	public void setEstRempli(boolean estRempli) {
		this.estRempliProperty.setValue(estRempli);
	}

	public static int getTempsRemplissage() {
		return TEMPS_REMPLISSAGE;
	}
	
	
}
