package sp;

/**
 * Main class of semestral work.
 * In this class are launched only necessary processes.
 * 
 * @author  Pavel Pr�cha and Tom� Sl�va
 */
public class Main {

	public static void main(String[] args) {

		DataInput.loadEntryValues();
		
		SimulationInput.loadSimulation();
	}
}