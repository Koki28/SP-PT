package sp;

public class Simulace {

	private int cas;
	private int zdroj;
	private int cil;
	private int data;
	
	public Simulace(int cas, int zdroj, int cil, int data) {
		
		this.cas = cas;
		this.zdroj = zdroj;
		this.cil = cil;
		this.data = data;
	}

	public int getCas() {
		
		return this.cas;
	}

	public int getZdroj() {
		
		return this.zdroj;
	}
	
	public int getCil() {
		
		return this.cil;
	}
	
	public int getData() {
		
		return this.data;
	}
}