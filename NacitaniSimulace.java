package sp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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

	private static Scanner sc = new Scanner(System.in);

	private static int ztratovostReseni = 0;

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

				if(dijkstra == null) {
					System.out.println("Cesta neexistuje.");
					return;
				}

				try (BufferedWriter bw = new BufferedWriter(new FileWriter("prubehSimulace.txt", true))) {
					System.out.println("Cesta z vrcholu " + zdroj + " do vrcholu " + cil  + ": ");
					
					bw.write("Cesta z vrcholu " + zdroj + " do vrcholu " + cil  + ": ");
					bw.newLine();
					for(Vrchol vrchol : dijkstra) {

						System.out.println(vrchol);
						bw.write("->" + vrchol);
						bw.newLine();
					} 

					PrechodDat prechod = new PrechodDat();
					System.out.println("\n<-------------------------------------->");
					bw.write("<-------------------------------------->");
					bw.newLine();
					prechod.posliData(cas-1, data, dijkstra);
					ztratovostReseni += prechod.vypisZtratovost();
					System.out.println("<-------------------------------------->");
					bw.write("<-------------------------------------->");
					bw.newLine();
					
					bw.close();
				}

				catch (Exception e) {

					System.err.println("Nepodaøilo se zapsat do souboru 'vstupTest.txt'.");
				}

			}

			/*	System.out.println();

			cesta.zpracujVrchol(graf.getVrchol(1));
			LinkedList <Vrchol> dijkstra = cesta.getCesta(graf.getVrchol(10));

			for(Vrchol vrchol : dijkstra) {

				System.out.println(vrchol);
			} */

			System.out.println("\nZtrátovost øešení je " + ztratovostReseni);

		} catch(FileNotFoundException e) {

			e.printStackTrace();
			System.err.println("Nepodaøilo se naèíst data ze souboru 'simulaceTest.txt'.");

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}