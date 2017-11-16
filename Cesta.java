package sp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Cesta {

	ArrayList <Vrchol> uzly;
	ArrayList <Hrana> propojeni;
	HashSet <Vrchol> prozkoumane;
	HashSet <Vrchol> neprozkoumane;
	HashMap <Vrchol, Vrchol> predchudce;
	HashMap <Vrchol, Integer> vzdalenost;

//	private ArrayList<Vrchol> nejkratsiCesta;
//	private ArrayList<Vrchol> prochazenaCesta;
	
	private Graf graf = NacitaniVstupnihoSouboru.getGraf();
	
	public Cesta() {
		
		this.uzly = new ArrayList <Vrchol>(graf.vrcholy);
		this.propojeni = new ArrayList <Hrana>(graf.hrany);
	}
	
	public void zpracujVrchol(Vrchol vrchol) {
		
		 prozkoumane = new HashSet <Vrchol>();
		 neprozkoumane = new HashSet <Vrchol>();
		 vzdalenost = new HashMap <Vrchol, Integer>();
		 predchudce = new HashMap <Vrchol, Vrchol>();
		 vzdalenost.put(vrchol, 0);
		 neprozkoumane.add(vrchol);
		 
		 while(neprozkoumane.size() > 0) {
		            
			 Vrchol uzel = getMinimum(neprozkoumane);
		     prozkoumane.add(uzel);
		     neprozkoumane.remove(uzel);
		     hledejNejkratsiCestu(uzel);
		 } 
	}
	
	public void hledejNejkratsiCestu(Vrchol vrchol) {
        
		ArrayList <Vrchol> sousedniVrcholy = getSousedi(vrchol);
        
		for(Vrchol cil : sousedniVrcholy) {
           
			if(getNejkratsiVzdalenost(cil) > (getNejkratsiVzdalenost(vrchol) + getVzdalenost(vrchol, cil))) {
                
            	vzdalenost.put(cil,(int)(getNejkratsiVzdalenost(vrchol) + getVzdalenost(vrchol, cil)));
                predchudce.put(cil, vrchol);
                neprozkoumane.add(cil);
            }
        }
    }
	
	public double getVzdalenost(Vrchol vrchol, Vrchol cil) {
        
		for (Hrana hrana : propojeni) {
            
			if (hrana.getPocatecni().equals(vrchol) && hrana.getKoncovy().equals(cil)) {
                
				return hrana.getPropustnostDijkstra();
            }
        }
		
        throw new RuntimeException("Chyba");
    }
	
	public ArrayList <Vrchol> getSousedi(Vrchol vrchol) {
		
        ArrayList <Vrchol> sousedi = new ArrayList<Vrchol>();
        
        for (Hrana hrana : propojeni) {
        	
            if (hrana.getPocatecni().equals(vrchol) && !jeVyrizeny(hrana.getKoncovy())) {
               
            	sousedi.add(hrana.getKoncovy());
            }
        }
        
        return sousedi;
    }
	
	public Vrchol getMinimum(HashSet <Vrchol> vrcholy) {
       
		Vrchol minimum = null;
        
		// pøepsat jinak
		for(Vrchol vrchol : vrcholy) {
            
			if (minimum == null) {
                
				minimum = vrchol;
				
            } else {
                
            	if(getNejkratsiVzdalenost(vrchol) < getNejkratsiVzdalenost(minimum)) {
            		
            		minimum = vrchol;
                }
            }
        }
		
        return minimum;
    }
	
	private int getNejkratsiVzdalenost(Vrchol cil) {
		
        Integer hodnota = vzdalenost.get(cil);
        
        if(hodnota == null) {
        	
            return Integer.MAX_VALUE;
            
        } else {
        	
            return hodnota;
        }
    }
	
	public boolean jeVyrizeny(Vrchol vrchol) {
        
		return prozkoumane.contains(vrchol);
    }
	
	public LinkedList <Vrchol> getCesta(Vrchol cil) {
        
		LinkedList <Vrchol> cesta = new LinkedList <Vrchol>();
        
		Vrchol krok = cil;
     
        if (predchudce.get(krok) == null) {
            
        	return null;
        }
        
        cesta.add(krok);
        
        while(predchudce.get(krok) != null) {
        	
            krok = predchudce.get(krok);
            cesta.add(krok);
        }
       
        Collections.reverse(cesta);
        
        return cesta;
    }
	
/*	public Cesta() {

		this.nejkratsiCesta = new ArrayList<Vrchol>();
		this.prochazenaCesta = new ArrayList<Vrchol>();
	} */

/*	public void najdiCestu(Vrchol zdroj, Vrchol cil) {
		
		Vrchol vrchol = zdroj;
			
			for (int i = 0; i < zdroj.sousedi.size(); i++) {
				
				Vrchol soused = zdroj.sousedi.get(i);
				hrana = graf.getHrana(vrchol, soused);
			
				prochazenaCesta.add(vrchol);
				prochazenaCesta.add(soused);
			
			
			
			
			}
	
	
	
	} */

/*	public void vypisCestu() {
		
		if (nejkratsiCesta.size() != 0) {
			
			int j = 0;
			System.out.println("Nejkratší cesta je: ");
		
			for(int i = 0; i < nejkratsiCesta.size()-1; i++) {
			
				System.out.print(nejkratsiCesta.get(i).getId() + " - ");
				j = i;
			}
		
			System.out.println(nejkratsiCesta.get(j+1).getId());
			System.out.println();
		}
	} */
}