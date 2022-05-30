package application.modele;

public abstract class Outil implements Item{
	
	private Environnement env;
	private int effet; //correspond au dégat infligé au bloc 
	
	public Outil(Environnement env, int effet) {
		this.env=env;
		this.effet=effet;
	}
	
	public Environnement getEnvironnement() {
		return env;
	}
}
