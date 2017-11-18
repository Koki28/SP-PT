package sp;

import java.util.Stack;

/**
 * Instance tøídy {@code StackPameti} pøedstavují zásobníky
 * pamìti jednotlivých uzlù sítì.
 *
 * @author  Pavel Prùcha a Tomáš Slíva
 */
public class StackPameti extends Stack {

	private int soucet;
	private final int MAX_PAMET = 800;
	
	/**
	 * Konstruktor vytváøející zásobník pamìti uzlu sítì.
	 */
	public StackPameti() {

	//	this.soucet = 0;
	}
	
	/**
	 * Metoda pøidávající pamì do zásobníku, pokud není v jednom kroku odeslána.
	 * 
	 * @param pridanaPamet
	 */
	public boolean pridatPamet(int pridanaPamet) {
		
		if(kontrolaPameti(pridanaPamet)) {
			
			soucet += pridanaPamet;
			return true;

		} else {
			
			return false;
		}
	}
	
	public boolean kontrolaPameti(int pridanaPamet) {
		
		 if ((pridanaPamet + soucet) > MAX_PAMET) {
			 
			 return false;
		 }
		
		return true;
	}
	
	public void posliZnovuData() {
		
		// ????
	}

	public void smazatData(int pozastavenaData) {
		soucet -= pozastavenaData;
		
	}
}