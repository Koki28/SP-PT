package sp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

/**
 * Pomocná tøída generující poèítaèovou sí typu topologie mesh.
 *
 * @author  Pavel Prùcha a Tomáš Slíva
 */
public class GenerovaniVstupnihoSouboru {

	private static Random r = new Random(); 

	/**
	* Metoda generující vstupní soubor poèítaèové sítì topologie mesh.
	* Kadı uzel sítì je propojenı se všemi ostatními uzly naší sítì.
	*/
	public static void generujVstupniSoubor() {

		for(int i = 1; i < 10; i++) {

			int j = i;

			while(j >= 1) {

				int propustnost = r.nextInt(9999) + 1;

				double chybovost = Math.round(r.nextDouble() * 100);
				chybovost /= 100;

				try (BufferedWriter bw = new BufferedWriter(new FileWriter("vstupTest.txt", true))) {

					bw.write((i + 1) + " - " + j + " - "  + propustnost   + " - " + chybovost);
					bw.newLine();
					bw.close();
				}
				
				catch (Exception e) {
					
					System.err.println("Nepodaøilo se zapsat do souboru 'vstupTest.txt'.");
				}
				
				j--;
			}
		}
	}

	public static void main(String[] args) {

		generujVstupniSoubor();
		
		System.out.println("KONEEEEEC!!!!");
	}
}

