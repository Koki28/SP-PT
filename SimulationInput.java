package sp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.scene.control.TextArea;

/**
 * Class loading entry values of simulation flow rate of computer web.
 * Result of this class is the shortest path between selected nodes.
 *
 * @author  Pavel Prùcha and Tomáš Slíva
 */
public class SimulationInput {

	private static Scanner sc = new Scanner(System.in);

	/** List of all requests. */
	private static List <Simulation> requests = new ArrayList <Simulation>();
	
	/**
	 * This method is loading entry values of simulation
	 * from the file. These values are evaluated and we 
	 * get the shortest path between selected nodes.
	 * 
	 * @param simulationFile  Name of simulation file.
	 * @param x  If false print into console, else into GUI.
	 * @param textArea  TextArea of GUI.
	 */
	public static void loadSimulation(String simulationFile, boolean x, TextArea textArea) {

		String simulation;
		
		if(!x) {
			
			System.out.println();
			System.out.println("Please, write a name of simulation file: ");
			String simulation1 = sc.next();
			System.out.println();
			simulation = simulation1;
			
		} else {

			String simulation1 = simulationFile;
			simulation = simulation1;
		}
		
		try (BufferedReader br = new BufferedReader(new FileReader(simulation))) {

			String row;

			while ((row = br.readLine()) != null) {

				String [] array = row.split(" - ");
					
				int time = 0;
				int source = 0;
				int target = 0;
				int data = 0;

				try {
				time = Integer.parseInt(array [0]);
				source = Integer.parseInt(array [1]);
				target = Integer.parseInt(array [2]);
				data = Integer.parseInt(array [3]);
				}
				catch (IllegalArgumentException e) {
					System.err.println("\nData are in illegal form\n");
					continue;
				}
				
				if (time <  0 || source < 0 || target < 0 || data < 0) {
					System.err.println("\nData are in illegal form\n");
					continue;
				}
				
				requests.add(new Simulation(time, source, target, data, null, null));
			}
			
			if(!x) {
				
				SendingData.completeRequests();

			} else {
				
				SendingData.completeRequests(x, textArea);
			}
			
			SendingData.writeFaulting(x, textArea);
			
		} catch(FileNotFoundException e) {

			e.printStackTrace();
			
			if(!x) {
				
				System.err.println("Simulation file not found!");
				
			} else {
				
				textArea.appendText("Simulation file not found!");
			}

		} catch (IOException e) {

			e.printStackTrace();
		} 
	}
	
	/**
	 * Returns list of all requests.
	 * 
	 * @return  List of all requests.
	 */
	public static List<Simulation> getRequests() {
		
		return requests;
	}
}