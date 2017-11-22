package sp;

/**
 * Main class of semestral work.
 * In this class are launched only necessary processes.
 * 
 * @author  Pavel Prùcha and Tomáš Slíva
 */
public class Main {

	public static void main(String[] args) {

		DataInput.loadEntryValues();
		
		SimulationInput.loadSimulation();
	}
}