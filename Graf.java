package sp;

import java.util.ArrayList;

public class Graf {

	public ArrayList <Vrchol> vrcholy;
	ArrayList <Hrana> hrany;

	public Graf() {

		this.vrcholy = new ArrayList <Vrchol>();
		this.hrany = new ArrayList <Hrana>();
	}

	public void pridejVrchol(int id) {

		Vrchol novyVrchol = new Vrchol(id);

		if(vrcholy.size() == 0) {

			vrcholy.add(novyVrchol);
		}

		for(int i = 0; i < vrcholy.size(); i++) {

			if (vrcholy.get(i).getId() == id) {

				return;
			}
		}

		vrcholy.add(novyVrchol);	
	}

	public void vypisVrcholy() {

		for (int i = 0; i < vrcholy.size(); i++) {

			System.out.println(vrcholy.get(i) + " ");
		}
	}

	public void sousedniVrcholy(Vrchol prvni, Vrchol druhy) {

		prvni.sousedi.add(druhy);
		druhy.sousedi.add(prvni);	
	}

	public void vypisSousedy(Vrchol vrchol) {
		
		System.out.println("Sousedi vrcholu " + vrchol + " jsou: ");

		for (int i = 0; i < vrchol.sousedi.size(); i++) {

			System.out.println("soused ---> "  + vrchol.sousedi.get(i) + "");
		}
		
		System.out.println();
	}

	Vrchol getVrchol(int id) {

		Vrchol vrchol = null;

		for (int i = 0; i < vrcholy.size(); i++) {

			if(vrcholy.get(i).id == id) {

				vrchol = vrcholy.get(i);
			}
		}

		return vrchol;
	}

	public void pridejHranu(Vrchol pocatecniUzel, Vrchol koncovyUzel, int propustnost, double chybovost) {
		
		Hrana novaHrana = new Hrana(pocatecniUzel, koncovyUzel, propustnost, chybovost);
		Hrana zpetnaHrana = new Hrana(koncovyUzel, pocatecniUzel, propustnost, chybovost);
		
		hrany.add(novaHrana);
		hrany.add(zpetnaHrana);
	}

	public void vypisHrany() {

		for (int i = 0; i < hrany.size(); i++) {

			System.out.println(hrany.get(i) + " ");
		}
	}

	public Hrana getHrana(Vrchol pocatecni, Vrchol koncovy) {

		Hrana hrana = null;

		for (int i = 0; i < hrany.size(); i++) {

			if ((hrany.get(i).getPocatecni().getId() == pocatecni.getId()) && (hrany.get(i).getKoncovy().getId() == koncovy.getId()) && 
				(hrany.get(i).getPocatecni().getId() == koncovy.getId()) && (hrany.get(i).getKoncovy().getId() == pocatecni.getId())) {

				hrana = hrany.get(i);
				return hrana;
			}
		}

		return null;
	}
}