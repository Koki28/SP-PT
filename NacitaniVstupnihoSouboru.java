package sp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class NacitaniVstupnihoSouboru {

	public static ArrayList <Vrchol> vsechnyVrcholy;
	
	public static void nactiVstup() {
		
		try (BufferedReader br = new BufferedReader(new FileReader("vstupTest.txt"))) {
			
			String radkaVstupu;
			
			vsechnyVrcholy = new ArrayList <Vrchol>();
			
			while ((radkaVstupu = br.readLine()) != null) {
				
				String [] data = radkaVstupu.split(" - ");
				
				int pocatecniUzel = Integer.parseInt(data [0]);
				int koncovyUzel = Integer.parseInt(data [1]);
				int propustnost = Integer.parseInt(data [2]);
				double chybovost = Double.parseDouble(data [3]);
				
				Vrchol kokot = new Vrchol(pocatecniUzel);
				Vrchol curak = new Vrchol(koncovyUzel);
				
				if(!(vsechnyVrcholy.contains(kokot))) {
					
					vsechnyVrcholy.add(kokot);
				}
				
				if(!(vsechnyVrcholy.contains(curak))) {
					
					vsechnyVrcholy.add(curak);
				} 
				
				System.out.println("ID_UZLU_" + pocatecniUzel + " - ID_UZLU_" + koncovyUzel + " - " + propustnost + " - " + chybovost);	
				
		    }
			System.out.println(vsechnyVrcholy.size());
			for (int i = 0; i < vsechnyVrcholy.size(); i++) {
				
				System.out.println(vsechnyVrcholy.get(i) + "");
			} 
			
			
		} catch(FileNotFoundException e) {
		
			e.printStackTrace();
			System.err.println("Nepodaøilo se naèíst data ze souboru 'vstupTest.txt'.");
		
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	}
}