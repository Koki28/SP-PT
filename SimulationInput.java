package sp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Class loading entry values of simulation flow rate of computer web.
 * Result of this class is the shortest path between selected nodes.
 *
 * @author  Pavel Prùcha and Tomáš Slíva
 */
public class SimulationInput {

	private static Scanner sc = new Scanner(System.in);

	/** Ammount of lost data. */
	private static int dataLost = 0;

	/**
	 * This method is loading entry values of simulation
	 * from the file. These values are evaluated and we 
	 * get the shortest path between selected nodes.
	 */
	public static void loadSimulation() {

		ArrayList <Simulation> simulationData = new ArrayList <Simulation>();

		System.out.println();
		System.out.println("Please, write a name of simulation file: ");
		String simulation = sc.next();
		
		try (BufferedReader br = new BufferedReader(new FileReader(simulation))) {

			String row;

			Graph graph = DataInput.getGraph();

			Path path = new Path();

			while ((row = br.readLine()) != null) {

				String [] array = row.split(" - ");

				int time = Integer.parseInt(array [0]);
				int source = Integer.parseInt(array [1]);
				int target = Integer.parseInt(array [2]);
				int data = Integer.parseInt(array [3]);

				simulationData.add(new Simulation(time, source, target, data));
				
				Node sourceNode = graph.getNode(source);
				Node targetNode = graph.getNode(target);

				System.out.println();

				path.examineNode(sourceNode);
				
				LinkedList <Node> dijkstra = path.getPath(targetNode);

				if(dijkstra == null) {
					
					System.out.println("Path doesn´t exist.");
					return;
				}

				try (BufferedWriter bw = new BufferedWriter(new FileWriter("simulation.txt", true))) {
					
					System.out.println("Path from node " + source + " to node " + target + ": ");
					
					bw.write("Path from node " + source + " to node " + target  + ": ");
					bw.newLine();
					
					for(Node node : dijkstra) {

						System.out.println(node);
						
						bw.write("->" + node);
						bw.newLine();
					} 

					SendingData request = new SendingData();
					
					System.out.println("\n<-------------------------------------->");
					
					bw.write("<-------------------------------------->");
					bw.newLine();
					
					request.sendData(time - 1, data, dijkstra);
					dataLost += request.writeFaulting();
					
					System.out.println("<-------------------------------------->");
					
					bw.write("<-------------------------------------->");
					bw.newLine();
					bw.close();
				}

				catch (Exception e) {

					System.err.println("Failed! Data aren´t recorded in the file.");
				}

			}

		/*	System.out.println();

			path.examineNode(graph.getNode(1));
			LinkedList <Node> dijkstra = path.getPath(graph.getNode(10));

			for(Node node : dijkstra) {

				System.out.println(node);
			} */

			System.out.println("\nFaulting this solution is " + dataLost);

		} catch(FileNotFoundException e) {

			e.printStackTrace();
			System.err.println("File not found.");

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}