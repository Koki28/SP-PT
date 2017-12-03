package sp;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TextArea;

/**
 * Instance of class {@code Graph} representing graph of 
 * computer web. It consists of all nodes and edges.
 *
 * @author  Pavel Prùcha and Tomáš Slíva
 */
public class Graph {

	/** List of all nodes. */
	public static List <Node> nodes = new ArrayList <Node>();;
	
	/** List of all edges. */
	public static List <Edge> edges = new ArrayList <Edge>();;

	/**
	 * Constructor creating graph of computer web.
	 */
	public Graph() {

	}

	/**
	 * This method is adding node into the graph
	 * only when the node is new in the graph.
	 * 
	 * param id  Node ID.
	 */
	public void addNode(int id) {

		Node newNode = new Node(id);

		if(nodes.size() == 0) {

			nodes.add(newNode);
		}

		for(int i = 0; i < nodes.size(); i++) {

			if (nodes.get(i).getId() == id) {

				return;
			}
		}

		nodes.add(newNode);	
	}

	/**
	 * This method is printing all nodes in the graph.
	 */
	public void printNodes() {

		for (int i = 0; i < nodes.size(); i++) {

			System.out.println(nodes.get(i) + " ");
		}
	}

	/**
	 * This method is adding two connected nodes
	 * into the list of adjacent nodes.
	 * 
	 * @param startNode  Start node of the edge.
	 * @param targetNode  Target node of the edge.
	 */
	public void adjacentNodes(Node startNode, Node targetNode) {

		startNode.neighbours.add(targetNode);
		targetNode.neighbours.add(startNode);	
	}

	/**
	 * This method is printing all neighbours of selected node.
	 * 
	 * @param node  Selected node.
	 * @param x  If false print into console, else into GUI.
	 * @param textArea  TextArea of GUI.
	 */
	public void printNeighbours(Node node, boolean x, TextArea textArea) {
		
		if(!x) {
			
			System.out.println("Neighbours of node " + node + " are: ");

			for (int i = 0; i < node.neighbours.size(); i++) {

				System.out.println("neighbour ---> " + node.neighbours.get(i) + "");
			}
			
			System.out.println();
		
		} else {
			
			String text = "";
			
			text = text + ("Neighbours of node " + node + " are: \n");
			
			for (int i = 0; i < node.neighbours.size(); i++) {

				text = text + ("neighbour ---> " + node.neighbours.get(i) + "\n");
			}
			
			textArea.appendText(text + "\n");
		}
	}

	/**
	 * Returns selected node.
	 * 
	 * @param id  ID of selected node.
	 * 
	 * @return  Selected node.
	 */
	public static Node getNode(int id) {

		Node node = null;

		for (int i = 0; i < nodes.size(); i++) {

			if(nodes.get(i).id == id) {

				node = nodes.get(i);
			}
		}

		return node;
	}

	/**
	 * This method is adding new edge into the graph.
	 *
	 * @param startNode  Start node of the edge.
	 * @param targetNode  Target node of the edge. 
	 * @param transmittance  Transmittance of the edge. 
	 * @param faulting  Faulting of the edge. 
	 */
	public void addEdge(Node startNode, Node targetNode, int transmittance, double faulting) {
		
		Edge newEdge = new Edge(startNode, targetNode, transmittance, faulting);
		Edge backEdge = new Edge(targetNode, startNode, transmittance, faulting);
		
		edges.add(newEdge);
		edges.add(backEdge);
	}

	/**
	 * This method is printing all edges of the graph.
	 * 
	 * @param x  If false print into console, else into GUI.
	 * @param textArea  TextArea of GUI.
	 */
	public void printEdges(boolean x, TextArea textArea) {

		if(!x) {
			
			for (int i = 0; i < edges.size(); i ++) {

				System.out.println(edges.get(i) + " ");
				i++;
			}
			
			System.out.println();
			
		} else {
			
			String text = "";
			
			for (int i = 0; i < edges.size(); i ++) {

				text = text + (edges.get(i) + "\n");
				i++;
			}
		
			textArea.appendText(text + "\n");
		}
	}

	/**
	 * Returns edge between two selected nodes.
	 * Returns null if the edge doesn´t exist.
	 * 
	 * @param startNode  Start node of the edge.
	 * @param targetNode  Target node of the edge.
	 * 
	 * @return  Edge between two selected nodes.
	 */
	public static Edge getEdge(Node startNode, Node targetNode) {

		Edge edge = null;

		for (int i = 0; i < edges.size(); i++) {

			if ((edges.get(i).getStartNode().getId() == startNode.getId()) && (edges.get(i).getTargetNode().getId() == targetNode.getId()) || 
				(edges.get(i).getStartNode().getId() == targetNode.getId()) && (edges.get(i).getTargetNode().getId() == startNode.getId())) {

				edge = edges.get(i);
				return edge;
			}
		}

		return null;
	}

	/**
	 * 
	 * 
	 * @param text1
	 * @param text2
	 * @param textArea
	 */
	public static void removeEdge(String text1, String text2, TextArea textArea) {
		
		int startNode = 0;
		int targetNode = 0;
		try {
			
		startNode = Integer.parseInt(text1);
		targetNode = Integer.parseInt(text2);
		}
		catch (IllegalArgumentException e) {
			textArea.appendText("\nNodes are in illegal form!\n");
			return;
		}
		if (startNode > Graph.getNodes().size() || targetNode > Graph.getNodes().size() || startNode <= 0 || targetNode <= 0) {
			textArea.appendText("\nEdge " + startNode + " - " + targetNode + " doesn´t exist!\n");
		}
		
		else if (getEdge(getNode(startNode), getNode(targetNode)) != null) {
			edges.remove(getEdge(getNode(startNode), getNode(targetNode)));
			edges.remove(getEdge(getNode(targetNode), getNode(startNode)));
			textArea.appendText("\nEdge " + startNode + " - " + targetNode + " was removed.\n");
		}
		else {
			textArea.appendText("\nEdge " + startNode + " - " + targetNode + " doesn´t exist!\n");
		}
		
	}

	/**
	 * Returns list of nodes.
	 * 
	 * @return  List of nodes.
	 */
	public static List<Node> getNodes() {
		
		return nodes;
	}

}