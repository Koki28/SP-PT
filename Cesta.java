package sp;

import java.util.ArrayList;

public class Cesta {

	private ArrayList<Vrchol> nejkratsiCesta;
	private ArrayList<Vrchol> prochazenaCesta;
	private Graf graf = NacitaniVstupnihoSouboru.getGraf();
	private Hrana hrana;
	

	public Cesta() {

		this.nejkratsiCesta = new ArrayList<Vrchol>();
		this.prochazenaCesta = new ArrayList<Vrchol>();
	}

	public void najdiCestu(Vrchol zdroj, Vrchol cil) {
		
		Vrchol vrchol = zdroj;
			
			for (int i = 0; i < zdroj.sousedi.size(); i++) {
				
			Vrchol soused = zdroj.sousedi.get(i);
			hrana = graf.getHrana(vrchol, soused);
			
			prochazenaCesta.add(vrchol);
			prochazenaCesta.add(soused);
			
			
			
			
			}
		
		
	
	
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
