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
	
	/** Scanner. */
	public static Scanner sc = new Scanner(System.in);
	
	/**
	 * Empty constructor of class DataInput.
	 */
	public DataInput() {
	}
	
	/**
	 * This method is loading entry values from the file.
	 * These values are inserted into the graph.
	 * 
	 * @param entryFile  Name of entry file.
	 * @param x  If false print into console, else into GUI.
	 * @param textArea  TextArea of GUI.
	 */
	public static void loadEntryValues(String entryFile, boolean x, TextArea textArea) {
		
		String entry;
		
		if(!x) {
			
			System.out.println("Please, write a name of entry file: ");
			String entry1 = sc.next();
			System.out.println();
			entry = entry1;
			
		} else {
			
			String entry1 = entryFile;
			entry = entry1;
		}
		
		try (BufferedReader br = new BufferedReader(new FileReader(entry))) {
		
			String row;
			int count = 0;
			
			graph = new Graph();
			
			while((row = br.readLine()) != null) {
				
				count++;
				
				String [] data = row.split(" - ");
				
				int startNode = Integer.parseInt(data [0]);
				int targetNode = Integer.parseInt(data [1]);
				int transmittance = Integer.parseInt(data [2]);
				double faulting = Double.parseDouble(data [3]);
				
				graph.addNode(startNode);
				graph.addNode(targetNode);
				
			    graph.addEdge(graph.getNode(startNode), graph.getNode(targetNode), transmittance, faulting);
				
				graph.adjacentNodes(graph.getNode(startNode), graph.getNode(targetNode));
		    }
			
			graph.printEdges(x, textArea);
			
			for(int i = 1; i < count; i++) {
				
				graph.printNeighbours(graph.getNode(i), x, textArea);
			}
				
		} catch(FileNotFoundException e) {
		
			e.printStackTrace();
			
			if(!x) {
				
				System.err.println("Entry file not found!");
				
			} else {
				
				textArea.appendText("Entry file not found!");
			}
		
		} catch (IOException e) {
		
			e.printStackTrace();
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