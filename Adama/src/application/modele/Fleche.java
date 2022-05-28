package application.modele;

public class Fleche extends Arme{
	
	private int porte;
	private int xProperty;
	private int yProperty;
	private final static int VITESSE = 2;
	
	
	public Fleche() {
		super(1, 1, 2);
		
	}

	@Override
	public void utiliser() {
		
		
	}
	
	public final int getX() {
		return this.xProperty.getValue();
	}
	
	public final void setX(int val) {
		this.xProperty.setValue(val);
	}

	public final IntegerProperty xProperty() {
		return this.xProperty;
	}

	public final int getY() {
		return this.yProperty.getValue();
	}

	public final void setY(int val) {
	this.yProperty.setValue(val);
	}

	public final IntegerProperty yProperty() {
		return this.yProperty;
	}
	
	public void droite(int val) {
		this.xProperty.setValue(this.getX() + val);
	}
	
	public void gauche(int val) { 
		this.droite(-val);
	}
	
	public void droite() { 
		this.droite(this.VITESSE);
	}
	
	public void gauche() {
		this.gauche(this.VITESSE);
	}
	
	public void tirer(boolean direction) {
		if(direction) {
			this.droite();
		}
		else {
			this.gauche();
		}
	}
	
	public void detruire() {
		this.setX(-32);
		this.setY(-32);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
