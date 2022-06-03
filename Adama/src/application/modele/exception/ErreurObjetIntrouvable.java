package application.modele.exception;



public class ErreurObjetIntrouvable extends Exception {
	public ErreurObjetIntrouvable(String str1, String str2) {
		super("Aucune instance de la classe " + str1 + " n'a pu être trouvé dans " + str2);
	}
}
