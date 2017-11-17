package sp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * T��da na��taj�c� hodnoty simulace pr�toku dat po��ta�ovu s�t�.
 * V�sledkem t�to t��dy je nejkrat�� cesta mezi zadan�mi vrcholy.
 *
 * @author  Pavel Pr�cha a Tom� Sl�va
 */
public class NacitaniSimulace {

	public static Scanner sc = new Scanner(System.in);

	/**
	 * Metoda na��t� po ��dc�ch vstupn� hodnoty ze souboru. 
	 * Ty jsou pot� zpracov�ny a z�sk�me nejkrat�� cestu 
	 * mezi zadan�mi vrcholy.
	 */
	public static void nactiSimulaci() {

		ArrayList <Simulace> simulaceData = new ArrayList <Simulace>();

		System.out.println();
		System.out.println("Napi�te n�zev souboru simulace: ");
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
					System.out.println("Pos�l�m data z vrcholu " +  prvni.getId() + " do vrcholu " + druhy.getId() + ": ");
					
					Hrana hrana = graf.getHrana(prvni, druhy);
					int propustnostHrany = hrana.getPropustnost(); 
					
					
					System.out.println("propustnost " + prvni.getId() + "->" + druhy.getId() + ": " + propustnostHrany);
					
					
					if(propustnostHrany > data) {
						System.out.println("Z vrcholu " + prvni.getId() + " do vrcholu " + druhy.getId() + " se poslala v�echna data(" + data + ").");
						//poslat data
						
					}
					
					else{
						int pozastavenaData = data - propustnostHrany;
						System.out.println("Z vrcholu " + prvni.getId() + " do vrcholu " + druhy.getId() + " bylo posl�no " + propustnostHrany + " dat.");
						//poslat data
						prvni.stack.pridatPamet(pozastavenaData);
						System.out.println(pozastavenaData + " dat bylo ulo�eno do stacku vrcholu " + prvni.getId() + ".");
						
						while(pozastavenaData != 0) {
						krok++;
						System.out.println("\n--- krok " + krok + " ---");
						
						if (propustnostHrany > pozastavenaData) {
							System.out.println("Z vrcholu " + prvni.getId() + " do vrcholu " + druhy.getId() + " se poslal zbytek dat(" + pozastavenaData + ").");
							//poslat data
							prvni.stack.smazatData(pozastavenaData);
							System.out.println(pozastavenaData + " dat bylo smaz�no ze stacku vrcholu " + prvni.getId() + ".");
							//pozastavenaData = 0;
							break;
						}
							System.out.println("Z vrcholu " + prvni.getId() + " do vrcholu " + druhy.getId() + " boly posl�no " + propustnostHrany + " dat.");
							//poslat data
							prvni.stack.smazatData(propustnostHrany);
							System.out.println(propustnostHrany + " dat bylo smaz�no ze stacku vrcholu " + prvni.getId() + ".");
							
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
			System.err.println("Nepoda�ilo se na��st data ze souboru 'simulaceTest.txt'.");

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}