package sp;

import java.io.IOException;

/**
 * Main class of semestral work.
 * In this class are launched only necessary processes.
 * 
 * @author  Pavel Pr�cha and Tom� Sl�va
 */
public class Main {

	public static void main(String[] args) {

		try {
			
			DataInput.loadEntryValues(false, null);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		SimulationInput.loadSimulation(false, null);
		
		Graph.statistic();
	}
}