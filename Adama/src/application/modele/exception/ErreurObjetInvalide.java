package application.modele.exception;

import application.modele.Item;

public class ErreurObjetInvalide extends Exception {
	public ErreurObjetInvalide(Item s) {
		super(s.getClass().getSimpleName() +  " est un objet invalide");
	}
}
