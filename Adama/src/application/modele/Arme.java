package application.modele;


public abstract class Arme implements Item  {
	private int degat;
	private int porter;
	private double tempsRecharge;
	private boolean recharger;
	
	public Arme(int degat, int porter, double tempsRecharge) {
		this.degat = degat;
		this.porter = porter;
		this.tempsRecharge = tempsRecharge;
		this.recharger = false;
	}
	
	public void multiplierTempsRecharge(int val) {
		this.tempsRecharge *= val; 
	}
	
	public void attaquer() {
		if (!this.recharger) {
			// attaquer à coder
			this.enRecharge();
			
		}
	}
	
	public void enRecharge() {
		this.recharger = true;
	}
	
	public void pasEnRecharge() {
		this.recharger = false;
	}
	
	/* public void setRecharge() {
		this.recharger = !this.recharger;
	} */
	
	public boolean estEnRecharge() {
		return this.recharger;
	}
}
