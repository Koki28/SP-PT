package sp;

import java.util.Stack;

/**
 * Instance t��dy {@code StackPameti} p�edstavuj� z�sobn�ky
 * pam�ti jednotliv�ch uzl� s�t�.
 *
 * @author  Pavel Pr�cha a Tom� Sl�va
 */
public class StackPameti extends Stack {

	private int soucet;
	private final int MAX_PAMET = 800;
	
	/**
	 * Konstruktor vytv��ej�c� z�sobn�k pam�ti uzlu s�t�.
	 */
	public StackPameti() {

	//	this.soucet = 0;
	}
	
	/**
	 * Metoda p�id�vaj�c� pam� do z�sobn�ku, pokud nen� v jednom kroku odesl�na.
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