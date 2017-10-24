package sp;

import java.util.ArrayList;

public class Graf {

	ArrayList <Uzel> uzly;
	ArrayList <Propojeni> propojeni;
	
	public Graf(ArrayList <Uzel> uzly, ArrayList <Propojeni> propojeni) {
		
		this.uzly = uzly;
		this.propojeni = propojeni;
	}
	
	// ?
	
	public void pridejUzel(int id) {
		
		Uzel novyUzel = new Uzel(id);
		
		novyUzel.id = id;
	}
	
	// ?
	
	public void pridejPropojeni(Uzel pocatecniUzel, Uzel koncovyUzel, int propustnost, double chybovost) {
		
		Propojeni novePropojeni = new Propojeni(pocatecniUzel, koncovyUzel, propustnost, chybovost);
		
		novePropojeni.pocatecniUzel = pocatecniUzel;
		novePropojeni.koncovyUzel = koncovyUzel;
		novePropojeni.propustnost = propustnost;
		novePropojeni.chybovost = chybovost;
	}
}
