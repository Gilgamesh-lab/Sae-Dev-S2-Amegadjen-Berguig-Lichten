
package application.modele;

public class Pierre extends Ressource {

	

	public Pierre(boolean posable, int x, int y, int indice) {
		super(posable, x, y, indice);
		
	}
	
	
	public Pierre(int indice) {
		super(false, 5,5, indice);
	}

	@Override
	public void utiliser() {
		// TODO Auto-generated method stub

	}


	@Override
	public void utiliser(int val) {
		// TODO Auto-generated method stub
		
	}

}