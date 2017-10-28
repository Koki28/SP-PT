package sp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.Scanner;

/**
 * Tøída naèítající hodnoty simulace prùtoku dat poèítaèovu sítí.
 * Výsledkem této tøídy je nejkratší cesta mezi zadanými vrcholy.
 *
 * @author  Pavel Prùcha a Tomáš Slíva
 */
public class NacitaniSimulace {

//	public static Scanner sc = new Scanner(System.in);
	
	/**
	* Metoda naèítá po øádcích vstupní hodnoty ze souboru. 
	* Ty jsou poté zpracovány a získáme nejkratší cestu 
	* mezi zadanými vrcholy.
	*/
	public static void nactiSimulaci() {
		
		ArrayList <Simulace> simulaceData = new ArrayList <Simulace>();
		
	//	System.out.println("Naspište název souboru simulace: ");
	//	String simulace = sc.next();
	//	try (BufferedReader br = new BufferedReader(new FileReader(simulace))) {
		
		try(BufferedReader br = new BufferedReader(new FileReader("simulaceTest.txt"))) {
			
			String radkaSimulace;
						
			Graf graf = NacitaniVstupnihoSouboru.getGraf();
			
			while ((radkaSimulace = br.readLine()) != null) {
			
				Cesta cesta = new Cesta();
				
				String [] pole = radkaSimulace.split(" - ");
				
				int cas = Integer.parseInt(pole [0]);
				int zdroj = Integer.parseInt(pole [1]);
				int cil = Integer.parseInt(pole [2]);
				int data = Integer.parseInt(pole [3]);
				
				simulaceData.add(new Simulace(cas, zdroj, cil, data));
				System.out.println("Cas: " + cas + " - zdroj: " + zdroj + " - cil: " + cil + " - data: " + data);	
				
				Vrchol zdrojV = graf.getVrchol(zdroj);
				Vrchol cilV = graf.getVrchol(cil);
				
				cesta.najdiCestu(zdrojV, cilV);
				
				cesta.vypisCestu();
		    }
	
		} catch(FileNotFoundException e) {
		
			e.printStackTrace();
			System.err.println("Nepodaøilo se naèíst data ze souboru 'simulaceTest.txt'.");
		
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	}
}