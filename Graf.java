package sp;

import java.util.ArrayList;

public class Graf {

	ArrayList <Vrchol> vrcholy;
	ArrayList <Hrana> hrany;
	
	public Graf() {
		
		this.vrcholy = new ArrayList <Vrchol>();
		this.hrany = new ArrayList <Hrana>();
	}
	
	public void pridejVrchol(int id) {
		int fail = 0;
		Vrchol novyVrchol = new Vrchol(id);
		
		if (vrcholy.size() == 0)
		vrcholy.add(novyVrchol);
		
		for (int i = 0; i < vrcholy.size(); i++) {
			
			if (vrcholy.get(i).getId() == id) {
				fail = 1;
			}
		}
		if (fail == 0)
				vrcholy.add(novyVrchol);
		
		/*if (!vrcholy.contains("ID uzlu :" + id)) 
		
		vrcholy.add(novyVrchol);*/
	}
	
	public void vypisVrcholy() {
		for (int i = 0; i < vrcholy.size(); i++) {
			System.out.println(vrcholy.get(i) + " ");
			
		}
		
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
		
		hrany.add(novaHrana);
		
		//novaHrana.sousedi.(koncovyUzel);
	}
	
	public void vypisHrany() {
		for (int i = 0; i < hrany.size(); i++) {
			System.out.println(hrany.get(i) + " ");
			
		}
	}
}

