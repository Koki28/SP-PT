package sp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class NacitaniVstupnihoSouboru {

	public static ArrayList<Integer> vsechnyVrcholy;
	
	public static void nactiVstup() {
		
		try (BufferedReader br = new BufferedReader(new FileReader("vstupTest.txt"))) {
			
			String radkaVstupu;
			
			vsechnyVrcholy = new ArrayList<Integer>();
			
			while ((radkaVstupu = br.readLine()) != null) {
				
				String [] data = radkaVstupu.split(" - ");
				
				int pocatecniUzel = Integer.parseInt(data [0]);
				int koncovyUzel = Integer.parseInt(data [1]);
				int propustnost = Integer.parseInt(data [2]);
				double chybovost = Double.parseDouble(data [3]);
				
				if(!(vsechnyVrcholy.contains(pocatecniUzel))) {
					
					vsechnyVrcholy.add(pocatecniUzel);
				}
				
				if(!(vsechnyVrcholy.contains(koncovyUzel))) {
					
					vsechnyVrcholy.add(koncovyUzel);
				} 
				
				System.out.println("ID_UZLU_" + pocatecniUzel + " - ID_UZLU_" + koncovyUzel + " - " + propustnost + " - " + chybovost);	
				
		    }
			
			for (int i = 0; i < vsechnyVrcholy.size(); i++) {
				
				System.out.println(vsechnyVrcholy.get(i));
			}
			
			
		} catch(FileNotFoundException e) {
		
			e.printStackTrace();
			System.err.println("Nepodaøilo se naèíst data ze souboru 'vstupTest.txt'.");
		
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	}
}