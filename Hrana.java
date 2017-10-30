package sp;

/**
 * Instance tøídy {@code Hrana} pøedstavují hrany urèující
 * strukturu poèítaèové sítì.
 *
 * @author  Pavel Prùcha a Tomáš Slíva
 */

public class Hrana {

	/** Poèáteèní uzel hrany. */
	Vrchol pocatecniUzel;
	
	/** Koncový uzel hrany. */
	Vrchol koncovyUzel;
	
	/** Propustnost hrany. */
	int propustnost;
	
	/** Chybovost hrany. */
	double chybovost;
	
	
	/**
     * Konstruktor vytváøející spojení mezi uzly poèítové sítì.
     *
     * @param pocatecniUzel  Poèáteèní uzel hrany.
     * @param koncovyUzel  Koncový uzel hrany. 
     * @param propustnost  Propustnost hrany. 
     * @param pocatecniUzel  Poèáteèní uzel hrany. 
     */
	public Hrana(Vrchol pocatecniUzel, Vrchol koncovyUzel, int propustnost, double chybovost) {
		
		this.pocatecniUzel = pocatecniUzel;
		this.koncovyUzel = koncovyUzel;
		this.propustnost = propustnost;
		this.chybovost = chybovost;
	}
	
	/**
	* Pøevede instanci na øetìzec obsahující poèáteèní uzel hrany, koncový uzel, její propustnost a chybovost.
	* 
	* @return Øetìzcová reprezentace dané hrany.
	*/
	public String toString() {
		
		String propojeni = pocatecniUzel + " - " + koncovyUzel + " - " + propustnost + " - " + chybovost;
		
		return propojeni;
	}
	
	public Vrchol getPocatecni() {
		
		return this.pocatecniUzel;
	}
	
	public Vrchol getKoncovy() {
		
		return this.koncovyUzel;
	}
	
	public int getPropustnost() {
		
		return this.propustnost;
	}
}