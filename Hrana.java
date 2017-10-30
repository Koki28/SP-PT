package sp;

/**
 * Instance t��dy {@code Hrana} p�edstavuj� hrany ur�uj�c�
 * strukturu po��ta�ov� s�t�.
 *
 * @author  Pavel Pr�cha a Tom� Sl�va
 */

public class Hrana {

	/** Po��te�n� uzel hrany. */
	Vrchol pocatecniUzel;
	
	/** Koncov� uzel hrany. */
	Vrchol koncovyUzel;
	
	/** Propustnost hrany. */
	int propustnost;
	
	/** Chybovost hrany. */
	double chybovost;
	
	
	/**
     * Konstruktor vytv��ej�c� spojen� mezi uzly po��tov� s�t�.
     *
     * @param pocatecniUzel  Po��te�n� uzel hrany.
     * @param koncovyUzel  Koncov� uzel hrany. 
     * @param propustnost  Propustnost hrany. 
     * @param pocatecniUzel  Po��te�n� uzel hrany. 
     */
	public Hrana(Vrchol pocatecniUzel, Vrchol koncovyUzel, int propustnost, double chybovost) {
		
		this.pocatecniUzel = pocatecniUzel;
		this.koncovyUzel = koncovyUzel;
		this.propustnost = propustnost;
		this.chybovost = chybovost;
	}
	
	/**
	* P�evede instanci na �et�zec obsahuj�c� po��te�n� uzel hrany, koncov� uzel, jej� propustnost a chybovost.
	* 
	* @return �et�zcov� reprezentace dan� hrany.
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