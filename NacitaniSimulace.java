package sp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class NacitaniSimulace {

	public static void nactiSimulaci() {
		
		ArrayList<Simulace> simulaceData = new ArrayList <Simulace>();
		
		try(BufferedReader br = new BufferedReader(new FileReader("simulaceTest.txt"))) {
			
			String radkaSimulace;
			
			while ((radkaSimulace = br.readLine()) != null) {
				
				String [] pole = radkaSimulace.split(" - ");
				
				int cas = Integer.parseInt(pole [0]);
				int zdroj = Integer.parseInt(pole [1]);
				int cil = Integer.parseInt(pole [2]);
				int data = Integer.parseInt(pole [3]);
				
				simulaceData.add(new Simulace(cas, zdroj, cil, data));
				System.out.println("Cas: " + cas + " - zdroj: " + zdroj + " - cil: " + cil + " - data: " + data);	
		    }
	
		} catch(FileNotFoundException e) {
		
			e.printStackTrace();
			System.err.println("Nepodaøilo se naèíst data ze souboru 'simulaceTest.txt'.");
		
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	}
}