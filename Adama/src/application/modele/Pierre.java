
package application.modele;

public class Pierre extends Ressource {

	

	public Pierre(int x, int y, boolean posable) {
		super(posable, x, y);
		
	}
	
	
	public Pierre() {
		super(false, 5,5);
	}

	@Override
	public void utiliser() {
		// TODO Auto-generated method stub

	}

}