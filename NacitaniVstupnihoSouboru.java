package sp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.util.Scanner;

public class NacitaniVstupnihoSouboru {
	
	//public static Scanner sc = new Scanner(System.in);

	public static void nactiVstup() {
		
	//	System.out.println("Naspište název souboru vstupních dat: ");
	//	String vstup = sc.next();
	//	try (BufferedReader br = new BufferedReader(new FileReader(vstup))) {
			
			
		try (BufferedReader br = new BufferedReader(new FileReader("vstupTest.txt"))) {
		
			String radkaVstupu;
			int pocet = 0;
			
			Graf graf = new Graf();
			
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
		//	graf.vypisHrany();
			
			for(int i = 1; i < pocet; i++) {
				
				graf.vypisSousedy(graf.getVrchol(i));

			}
			
		} catch(FileNotFoundException e) {
		
			e.printStackTrace();
			System.err.println("Nepodaøilo se naèíst data ze souboru 'vstupTest.txt'.");
		
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	}
}