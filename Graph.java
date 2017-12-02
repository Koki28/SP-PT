package sp;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TextArea;

/**
 * Instance of class {@code Graph} representing graph of 
 * computer web. It consists of all nodes and edges.
 *
 * @author  Pavel Pr�cha and Tom� Sl�va
 */
public class Graph {

	/** List of all nodes. */
	public List <Node> nodes;
	
	/** List of all edges. */
	public List <Edge> edges;

	/**
	 * Constructor creating graph of computer web.
	 */
	public Graph() {

		this.nodes = new ArrayList <Node>();
		this.edges = new ArrayList <Edge>();
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
	public Node getNode(int id) {

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
	 * Returns null if the edge doesn�t exist.
	 * 
	 * @param startNode  Start node of the edge.
	 * @param targetNode  Target node of the edge.
	 * 
	 * @return  Edge between two selected nodes.
	 */
	public Edge getEdge(Node startNode, Node targetNode) {

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
	 * ud�lat
	 */
	public static void removeEdge() {
		
		
	}
}