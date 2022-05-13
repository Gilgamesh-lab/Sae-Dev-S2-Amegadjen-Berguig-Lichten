import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Arme implements Item  {
	private int degat;
	private IntegerProperty porter;
	private DoubleProperty tempsRecharge;
	
	public Arme(int degat, int porter, double tempsRecharge) {
		this.degat = degat;
		this.porter = new SimpleIntegerProperty(porter);
		this.tempsRecharge = new SimpleDoubleProperty(tempsRecharge);
	}
	
	
	
	public void attaquer() {
		
	}
	
	
	
}
