package sp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

/**
 * Pomocn� t��da generuj�c� po��ta�ovou s� typu topologie mesh.
 *
 * @author  Pavel Pr�cha a Tom� Sl�va
 */
public class GenerovaniVstupnihoSouboru {

	private static Random r = new Random(); 

	/**
	* Metoda generuj�c� vstupn� soubor po��ta�ov� s�t� topologie mesh.
	* Ka�d� uzel s�t� je propojen� se v�emi ostatn�mi uzly na�� s�t�.
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
					
					System.err.println("Nepoda�ilo se zapsat do souboru 'vstupTest.txt'.");
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

