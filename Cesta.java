package sp;

import java.util.ArrayList;

public class Cesta {

	private ArrayList<Vrchol> nejkratsiCesta;
	private ArrayList<Vrchol> pouzivanaCestaChceToJinyNazev;
	private Hrana hrana;

	public Cesta() {

		this.nejkratsiCesta = new ArrayList<Vrchol>();
		this.pouzivanaCestaChceToJinyNazev = new ArrayList<Vrchol>();
	}

	public void najdiCestu(Vrchol zdroj, Vrchol cil) {
		
		Vrchol vrchol = zdroj;
		int index = 0;
		
		while(vrchol.sousedi.get(index) == null) {
			
			Vrchol soused = zdroj.sousedi.get(index);
			hrana = new Hrana();
			
			
			
			
		}
		zdroj.sousedi.get();
		// nejakej ten dijkstra ??
		
	
	
	}

	public void vypisCestu() {
		
		if (nejkratsiCesta.size() != 0) {
			
		int j = 0;
		
		System.out.println("Nejkratší cesta je: ");
		
		for (int i = 0; i < nejkratsiCesta.size()-1; i++) {
			
			System.out.print(nejkratsiCesta.get(i).getId() + " - ");
			j = i;
		}
		
		System.out.println(nejkratsiCesta.get(j+1).getId());
		System.out.println();
		}
	}


}
