package sp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class loading entry values of simulation flow rate of computer web.
 * Result of this class is the shortest path between selected nodes.
 *
 * @author  Pavel Prùcha and Tomáš Slíva
 */
public class SimulationInput {

	private static Scanner sc = new Scanner(System.in);

	/** List of all requests. */
	private static ArrayList <Simulation> requests = new ArrayList <Simulation>();
	
	/**
	 * Empty constructor of class SimulationInput.
	 */
	public SimulationInput() {
		
	}

	/**
	 * This method is loading entry values of simulation
	 * from the file. These values are evaluated and we 
	 * get the shortest path between selected nodes.
	 */
	public static void loadSimulation() {

		System.out.println();
		System.out.println("Please, write a name of simulation file: ");
		String simulation = sc.next();

		try (BufferedReader br = new BufferedReader(new FileReader(simulation))) {

			String row;

			while ((row = br.readLine()) != null) {

				String [] array = row.split(" - ");

				int time = Integer.parseInt(array [0]);
				int source = Integer.parseInt(array [1]);
				int target = Integer.parseInt(array [2]);
				int data = Integer.parseInt(array [3]);

				requests.add(new Simulation(time, source, target, data, null, null));
			}
			
			SendingData.completeRequests();
			SendingData.writeFaulting();
			
		} catch(FileNotFoundException e) {

			e.printStackTrace();
			System.err.println("File not found.");

		} catch (IOException e) {

			e.printStackTrace();
		} 
	}
	
	/**
	 * Returns list of all requests.
	 * 
	 * @return  List of all requests.
	 */
	public static ArrayList <Simulation> getRequests() {
		
		return requests;
	}
}