package sp;

import java.util.ArrayList;

/**
 * Instance tøídy {@code Vrchol} pøedstavují vrcholy urèující
 * strukturu poèítaèové sítì.
 *
 * @author  Pavel Prùcha a Tomáš Slíva
 */
public class Vrchol {

	/** ID uzlu. */
	public int id;
	
	/** Zásobník pamìti uzlu. */
    public StackPameti stack;
    
    /** Seznam sousedních vrcholù. */
	public ArrayList <Vrchol> sousedi;
	
   /**
    * Konstruktor vytváøející vrchol poèítové sítì.
    *
    * @param id  ID uzlu.
    */
	public Vrchol(int id) {
		
		this.id = id;
		this.sousedi = new ArrayList <Vrchol>();
		this.stack = new StackPameti();
	}
	
   /**
	* Vrací ID uzlu.
	* 
	* @return  ID uzlu.
	*/
	public int getId() {
		
		return this.id;
	}
	
	/**
	* Pøevede instanci na øetìzec obsahující ID uzlu.
	* 
	* @return Øetìzcová reprezentace daného vrcholu.
	*/
	@Override
	public String toString() {
		
		String uzel = "ID uzlu: " + id;
		
		return uzel;
	}
}