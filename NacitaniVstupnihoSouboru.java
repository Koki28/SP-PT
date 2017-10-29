package sp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.util.Scanner;

/**
 * Třída načítající vstupní hodnoty počítačové sítě.
 * Načtené hodnoty se poté vloží do grafu.
 *
 * @author  Pavel Průcha a Tomáš Slíva
 */
public class NacitaniVstupnihoSouboru {
	
	/** Graf počítačové sítě */
	public static Graf graf;
	
	//public static Scanner sc = new Scanner(System.in);

	/**
	* Metoda načítá po řádcích vstupní hodnoty ze
	* souboru, které posléze vkládá do grafu.
	*/
	public static void nactiVstup() {
		
	//	System.out.println("Naspište název souboru vstupních dat: ");
	//	String vstup = sc.next();
	//	try (BufferedReader br = new BufferedReader(new FileReader(vstup))) {
			
		try (BufferedReader br = new BufferedReader(new FileReader("vstupTest.txt"))) {
		
			String radkaVstupu;
			int pocet = 0;
			
			graf = new Graf();
			
			while ((radkaVstupu = br.readLine()) != null) {
				
				pocet++;
				
				String [] data = radkaVstupu.split(" - ");
				
				int pocatecniUzel = Integer.parseInt(data [0]);
				int koncovyUzel = Integer.parseInt(data [1]);
				int propustnost = Integer.parseInt(data [2]);
				double chybovost = Double.parseDouble(data [3]);
				
				graf.pridejVrchol(pocatecniUzel);
				graf.pridejVrchol(koncovyUzel);
				
			//	System.out.println("ID_UZLU_" + pocatecniUzel + " - ID_UZLU_" + koncovyUzel + " - " + propustnost + " - " + chybovost);	
				
			    graf.pridejHranu(graf.getVrchol(pocatecniUzel), graf.getVrchol(koncovyUzel), propustnost, chybovost);
				
				graf.sousedniVrcholy(graf.getVrchol(pocatecniUzel), graf.getVrchol(koncovyUzel));
				
			//	graf.vypisSousedy(graf.getVrchol(pocatecniUzel));
		    }
			
		//	graf.vypisVrcholy();
			graf.vypisHrany();
			
		/*	for(int i = 1; i < pocet; i++) {
				
				graf.vypisSousedy(graf.getVrchol(i));
			}*/
			
		} catch(FileNotFoundException e) {
		
			e.printStackTrace();
			System.err.println("Nepodařilo se načíst data ze souboru 'vstupTest.txt'.");
		
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	}
	
   /**
	* Vrací graf, tedy jeho seznam vrcholů a hran.
	* 
	* @return  Vytvořený graf.
	*/
	public static Graf getGraf() {
		
		return graf;
	}
}