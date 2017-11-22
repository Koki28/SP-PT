package sp;

/**
 * Instance of class {@code Edge} representing edges of computer web.
 *
 * @author  Pavel Prùcha and Tomáš Slíva
 */
public class Edge {

	/** Start node of the edge. */
	private Node startNode;

	/** Target node of the edge. */
	private Node targetNode;

	/** Transmittance of the edge. */
	private int transmittance;

	/** Faulting of the edge. */
	private double faulting;
	
	/**
	 * Constructor creating connection between nodes of computer web.
	 *
	 * @param startNode  Start node of the edge.
	 * @param targetNode  Target node of the edge. 
	 * @param transmittance  Transmittance of the edge. 
	 * @param faulting  Faulting of the edge. 
	 */
	public Edge(Node startNode, Node targetNode, int transmittance, double faulting) {

		this.startNode = startNode;
		this.targetNode = targetNode;
		this.transmittance = transmittance;
		this.faulting = faulting;
	}

	/**
	 * Returns start node of the edge.
	 * 
	 * @return  Start node of the edge.
	 */
	public Node getStartNode() {

		return this.startNode;
	}

	/**
	 * Returns target node of the edge.
	 * 
	 * @return  Target node of the edge.
	 */
	public Node getTargetNode() {

		return this.targetNode;
	}

	/** 
	 * Returns transmittance of the edge for finding the shortest path.
	 * 
	 * @return  Transmittance of the edge for Dijkstra algorithm.
	 */
	public double getTransmittanceDijkstra() {

		return this.transmittance;
	}
	
	/** 
	 * Returns transmittance of the edge.
	 * 
	 * @return  Transmittance of the edge.
	 */
	public int getTransmittance() {

		return this.transmittance;
	} 
	
	/**
	 * Returns faulting of the edge.
	 * 
	 * @return  Faulting of the edge.
	 */
	public double getFaulting() {

		return this.faulting;
	}
	
	/**
	* Converts instance to string containing start node,
	* target node, transmittance and faulting of the edge.
	* 
	* @return  String representation of given edge.
	*/
	@Override
	public String toString() {

		String edge = startNode + " - " + targetNode + " - " + transmittance + " - " + faulting;

		return edge;
	}
}