package sp;

import java.util.ArrayList;

public class Cesta {

	private ArrayList<Vrchol> nejkratsiCesta;
	private ArrayList<Vrchol> pouzivanaCestaChceToJinyNazev;

	public Cesta() {

		this.nejkratsiCesta = new ArrayList<Vrchol>();
		this.pouzivanaCestaChceToJinyNazev = new ArrayList<Vrchol>();
	}

	public void najdiCestu(Vrchol zdroj, Vrchol cil) {
		
		
		nejkratsiCesta.add(zdroj);
		nejkratsiCesta.add(cil);
	
	
	}

	public void vypisCestu() {
		int j = 0;
		System.out.println(nejkratsiCesta.size());
		for (int i = 0; i < nejkratsiCesta.size()-1; i++) {
			
			System.out.print(nejkratsiCesta.get(i).getId() + " - ");
			j = i;
		}
		System.out.println(nejkratsiCesta.get(j+1).getId());
	}


}
