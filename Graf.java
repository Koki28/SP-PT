package sp;

import java.util.ArrayList;

public class Graf {

	ArrayList <Vrchol> vrcholy;
	ArrayList <Hrana> hrany;
	
	public Graf(ArrayList <Vrchol> vrcholy, ArrayList <Hrana> hrany) {
		
		this.vrcholy = new ArrayList <Vrchol>();
		this.hrany = new ArrayList <Hrana>();
	}
	
	public void pridejVrchol(int id) {
		
		Vrchol novyVrchol = new Vrchol(id);
		
		vrcholy.add(novyVrchol);
	}
	
	public void pridejHranu(Vrchol pocatecniUzel, Vrchol koncovyUzel, int propustnost, double chybovost) {
		
		Hrana novaHrana = new Hrana(pocatecniUzel, koncovyUzel, propustnost, chybovost);
		
		//novaHrana.sousedi.(koncovyUzel);
	}
}
