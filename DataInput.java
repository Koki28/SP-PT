package sp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javafx.scene.control.TextArea;

/**
 * Class loading entry values of computer web.
 * Entry values are inserted into the graph.
 *
 * @author  Pavel Prùcha and Tomáš Slíva
 */
public class DataInput {
	
	/** Graph of computer web. */
	public static Graph graph;
	
	public static Scanner sc = new Scanner(System.in);
	
	/**
	 * This method is loading entry values from the file.
	 * These values are inserted into the graph.
	 * 
	 * @param x  If false print into console, else into GUI.
	 * @param textArea  TextArea of GUI.
	 *
	 * @throws IOException  IOException.
	 */
	public static void loadEntryValues(boolean x, TextArea textArea) throws IOException {
		
		System.out.println("Please, write a name of entry file: ");
		String entry = sc.next();
		System.out.println();

		try (BufferedReader br = new BufferedReader(new FileReader(entry))) {
		
			String row;
			
			graph = new Graph();
			
			while((row = br.readLine()) != null) {
				
				String [] data = row.split(" - ");
				
				int startNode = 0;
				int targetNode = 0;
				int transmittance = 0;
				double faulting = 0.0;
				
				try {
				
					startNode = Integer.parseInt(data [0]);
					targetNode = Integer.parseInt(data [1]);
					transmittance = Integer.parseInt(data [2]);
					faulting = Double.parseDouble(data [3]);
				
				} catch(IllegalArgumentException e) {
					
					System.err.println("\nData are in illegal form.\n");
					continue;
				}
				
				if (startNode <  0 || targetNode < 0 || transmittance < 0 || faulting < 0 || faulting > 1) {

					System.err.println("\nData are in illegal form.\n");
					continue;
				}
				
				graph.addNode(startNode);
				graph.addNode(targetNode);
				
			    graph.addEdge(Graph.getNode(startNode), Graph.getNode(targetNode), transmittance, faulting);
				
				graph.adjacentNodes(Graph.getNode(startNode), Graph.getNode(targetNode));
		    }
			
			graph.printEdges(x, textArea);
			
			printAllNeighbours(x, textArea);
				
		} catch(FileNotFoundException e) {
		
			e.printStackTrace();
		
			System.err.println("Entry file not found!");	
		}
	}
	
	/**
	 * This method is loading entry values from the file.
	 * These values are inserted into the graph.
	 * 
	 * @param entryFile  Name of entry file.
	 * @param x  If false print into console, else into GUI.
	 * @param textArea  TextArea of GUI.
	 * 
	 * @throws IOException  IOException.
	 */
	public static void loadEntryValues(String entryFile, boolean x, TextArea textArea) throws IOException {

		try (BufferedReader br = new BufferedReader(new FileReader(entryFile))) {
		
			String row;
			
			graph = new Graph();
			
			while((row = br.readLine()) != null) {
				
				String [] data = row.split(" - ");
				
				int startNode = 0;
				int targetNode = 0;
				int transmittance = 0;
				double faulting = 0.0;
				
				try {
				
					startNode = Integer.parseInt(data [0]);
					targetNode = Integer.parseInt(data [1]);
					transmittance = Integer.parseInt(data [2]);
					faulting = Double.parseDouble(data [3]);
				
				} catch(IllegalArgumentException e) {
					
					textArea.appendText("Data are in illegal form.\n\n");
					continue;
				}
				
				if (startNode <  0 || targetNode < 0 || transmittance < 0 || faulting < 0 || faulting > 1) {
					
					textArea.appendText("Data are in illegal form.\n\n");
					continue;
				}
				
				graph.addNode(startNode);
				graph.addNode(targetNode);
				
			    graph.addEdge(Graph.getNode(startNode), Graph.getNode(targetNode), transmittance, faulting);
				
				graph.adjacentNodes(Graph.getNode(startNode), Graph.getNode(targetNode));
		    }
			
			graph.printEdges(x, textArea);
			
			printAllNeighbours(x, textArea);
				
		} catch(FileNotFoundException e) {
		
			e.printStackTrace();
			
			textArea.appendText("\nEntry file not found!");
		}
	}
	
	/**
	 * This method is printing all neighbours of all nodes.
	 * 
	 * @param x  If false print into console, else into GUI.
	 * @param textArea  TextArea of GUI.
	 */
	public static void printAllNeighbours(boolean x, TextArea textArea) {
		
		for(int i = 1; i < Graph.getNodes().size()+1; i++) {
			
			graph.printNeighbours(Graph.getNode(i), x, textArea);
		}
	}
	
   /**
	* Returns graph, the list of its nodes and edges.
	* 
	* @return  Created graph.
	*/
	public static Graph getGraph() {
		
		return graph;
	}
}