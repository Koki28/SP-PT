package sp;

public class Hrana {

	Vrchol pocatecniUzel;
	Vrchol koncovyUzel;
	int propustnost;
	double chybovost;
	
	public Hrana(Vrchol pocatecniUzel, Vrchol koncovyUzel, int propustnost, double chybovost) {
		
		this.pocatecniUzel = pocatecniUzel;
		this.koncovyUzel = koncovyUzel;
		this.propustnost = propustnost;
		this.chybovost = chybovost;
	}
	
	public String toString() {
		
		String propojeni = pocatecniUzel + " - " + koncovyUzel + " - " + propustnost + " - " + chybovost;
		
		return propojeni;
	}
}