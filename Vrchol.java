package sp;

import java.util.ArrayList;

/**
 * Instance t��dy {@code Vrchol} p�edstavuj� vrcholy ur�uj�c�
 * strukturu po��ta�ov� s�t�.
 *
 * @author  Pavel Pr�cha a Tom� Sl�va
 */
public class Vrchol {

	/** ID uzlu. */
	public int id;
	
	/** Z�sobn�k pam�ti uzlu. */
    public StackPameti stack;
    
    /** Seznam sousedn�ch vrchol�. */
	public ArrayList <Vrchol> sousedi;
	
   /**
    * Konstruktor vytv��ej�c� vrchol po��tov� s�t�.
    *
    * @param id  ID uzlu.
    */
	public Vrchol(int id) {
		
		this.id = id;
		this.sousedi = new ArrayList <Vrchol>();
		this.stack = new StackPameti();
	}
	
   /**
	* Vrac� ID uzlu.
	* 
	* @return  ID uzlu.
	*/
	public int getId() {
		
		return this.id;
	}
	
	/**
	* P�evede instanci na �et�zec obsahuj�c� ID uzlu.
	* 
	* @return �et�zcov� reprezentace dan�ho vrcholu.
	*/
	@Override
	public String toString() {
		
		String uzel = "ID uzlu: " + id;
		
		return uzel;
	}
}