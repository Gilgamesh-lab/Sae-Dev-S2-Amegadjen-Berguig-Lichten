
public class Lancement {

	public static void main(String[] args) throws ErreurInventairePlein, InterruptedException {
		int[][] carte = null;
		Environnement env =  new Environnement (carte);
		Checkpoint checkpoint = new Checkpoint(5,2,env);
		Item main =  new Poing();
		Inventaire  inventaire = new Inventaire(1);
		Inventaire  inventaire2 = new Inventaire(5);
		Joueur joueur = new Joueur(checkpoint);
		
		System.out.println(joueur.getFaim());
		joueur.setFaim(4);
		System.out.println(joueur.getFaim());
		joueur.manger(2);
		System.out.println(joueur.getFaim());
		joueur.manger(5);
		System.out.println(joueur.getFaim());
		joueur.appetit();;
		System.out.println(joueur.getFaim());
		
		/*inventaire2.ajouter(main);
		inventaire2.ajouter(main);
		inventaire2.ajouter(main);
		// inventaire2.fusionnerInventaire(inventaire);
		
		
		
		System.out.println(joueur.getInventaire().getTaille());
		System.out.println(joueur.getInventaire().estPlein());
		joueur.getInventaire().ajouter(main);
		joueur.attaquer();
		System.out.println(joueur.getInventaire().estPlein());*/
		
		

	}

}
