package sp;

public class StackPameti {

	public int soucet;
	public final int MAX_PAMET = 100;
	
	public StackPameti() {
		this.soucet = soucet;
	
		
	}
	
	public void pridatPamet(int pridanaPamet) {
		
		if (kontrolaPameti(pridanaPamet))
			soucet += pridanaPamet;
		
		else
			posliZnovuData();

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
	
}
