package sp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Tøída naèítající hodnoty simulace prùtoku dat poèítaèovu sítí.
 * Výsledkem této tøídy je nejkratší cesta mezi zadanými vrcholy.
 *
 * @author  Pavel Prùcha a Tomáš Slíva
 */
public class NacitaniSimulace {

	public static Scanner sc = new Scanner(System.in);

	/**
	 * Metoda naèítá po øádcích vstupní hodnoty ze souboru. 
	 * Ty jsou poté zpracovány a získáme nejkratší cestu 
	 * mezi zadanými vrcholy.
	 */
	public static void nactiSimulaci() {

		ArrayList <Simulace> simulaceData = new ArrayList <Simulace>();

		System.out.println();
		System.out.println("Napište název souboru simulace: ");
		String simulace = sc.next();
		try (BufferedReader br = new BufferedReader(new FileReader(simulace))) {

			//	try(BufferedReader br = new BufferedReader(new FileReader("simulaceTest.txt"))) {

			String radkaSimulace;

			Graf graf = NacitaniVstupnihoSouboru.getGraf();

			Cesta cesta = new Cesta();

			while ((radkaSimulace = br.readLine()) != null) {

				//	Cesta cesta = new Cesta();

				String [] pole = radkaSimulace.split(" - ");

				int cas = Integer.parseInt(pole [0]);
				int zdroj = Integer.parseInt(pole [1]);
				int cil = Integer.parseInt(pole [2]);
				int data = Integer.parseInt(pole [3]);

				simulaceData.add(new Simulace(cas, zdroj, cil, data));
				//System.out.println("Cas: " + cas + " - zdroj: " + zdroj + " - cil: " + cil + " - data: " + data);	

				Vrchol zdrojV = graf.getVrchol(zdroj);
				Vrchol cilV = graf.getVrchol(cil);

				//cesta.najdiCestu(zdrojV, cilV);
				//cesta.vypisCestu();

				System.out.println();

				cesta.zpracujVrchol(zdrojV);
				LinkedList <Vrchol> dijkstra = cesta.getCesta(cilV);

				if(dijkstra == null)
					System.out.println("Cesta neexistuje.");
				else
					System.out.println("Cesta z vrcholu " + zdroj + " do vrcholu " + cil  + ": ");
				for(Vrchol vrchol : dijkstra) {

					System.out.println(vrchol);
				} 
				
				
				
				System.out.println("<-------------------------------------->");
				int krok = 0;
				for(int i = 0; i < dijkstra.size()-1; i++) {
					krok++;
					
					
					
					Vrchol prvni = dijkstra.get(i); 
					Vrchol druhy = dijkstra.get(i+1); 
					System.out.println("\n--- krok " + krok + " ---");
					System.out.println("Posílám data z vrcholu " +  prvni.getId() + " do vrcholu " + druhy.getId() + ": ");
					
					Hrana hrana = graf.getHrana(prvni, druhy);
					int propustnostHrany = hrana.getPropustnost(); 
					
					
					System.out.println("propustnost " + prvni.getId() + "->" + druhy.getId() + ": " + propustnostHrany);
					
					
					if(propustnostHrany > data) {
						System.out.println("Z vrcholu " + prvni.getId() + " do vrcholu " + druhy.getId() + " se poslala všechna data(" + data + ").");
						//poslat data
						
					}
					
					else{
						int pozastavenaData = data - propustnostHrany;
						System.out.println("Z vrcholu " + prvni.getId() + " do vrcholu " + druhy.getId() + " bylo posláno " + propustnostHrany + " dat.");
						//poslat data
						prvni.stack.pridatPamet(pozastavenaData);
						System.out.println(pozastavenaData + " dat bylo uloženo do stacku vrcholu " + prvni.getId() + ".");
						
						while(pozastavenaData != 0) {
						krok++;
						System.out.println("\n--- krok " + krok + " ---");
						
						if (propustnostHrany > pozastavenaData) {
							System.out.println("Z vrcholu " + prvni.getId() + " do vrcholu " + druhy.getId() + " se poslal zbytek dat(" + pozastavenaData + ").");
							//poslat data
							prvni.stack.smazatData(pozastavenaData);
							System.out.println(pozastavenaData + " dat bylo smazáno ze stacku vrcholu " + prvni.getId() + ".");
							//pozastavenaData = 0;
							break;
						}
							System.out.println("Z vrcholu " + prvni.getId() + " do vrcholu " + druhy.getId() + " boly posláno " + propustnostHrany + " dat.");
							//poslat data
							prvni.stack.smazatData(propustnostHrany);
							System.out.println(propustnostHrany + " dat bylo smazáno ze stacku vrcholu " + prvni.getId() + ".");
							
							pozastavenaData -= propustnostHrany;
							
							
						}
						
					}
					
					
				}
				System.out.println("<-------------------------------------->");
				
				
				
				
				
			}

		/*	System.out.println();

			cesta.zpracujVrchol(graf.getVrchol(1));
			LinkedList <Vrchol> dijkstra = cesta.getCesta(graf.getVrchol(10));

			for(Vrchol vrchol : dijkstra) {

				System.out.println(vrchol);
			} */




			 












		} catch(FileNotFoundException e) {

			e.printStackTrace();
			System.err.println("Nepodaøilo se naèíst data ze souboru 'simulaceTest.txt'.");

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}