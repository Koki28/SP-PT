package sp;

/**
 * Instance of class {@code Simulation} representing
 * one request of sending data package.
 *
 * @author  Pavel Prùcha and Tomáš Slíva
 */
public class Simulation {

	/** Starting time of request. */
	private int time;
	
	/** Source of request. */
	private int source;
	
	/** Target of request. */
	private int target;
	
	/** Data package. */
	private int data;
	
	/** Node, where is data package saved. */
	private Node stackedNode;
	
	/** Prime node of default dijkstra. */
	private Node primeNode;
	
	/**
	 * Constructor creating one request.
	 * 
	 * @param time  Starting time of request.
	 * @param source  Source of request.
	 * @param target  Target of request.
	 * @param data  Data package.
	 * @param stackedNode  Node, where is data package saved.
	 * @param primeNode  Prime node of default dijkstra.
	 */
	public Simulation(int time, int source, int target, int data, Node stackedNode, Node primeNode) {
		
		this.time = time;
		this.source = source;
		this.target = target;
		this.data = data;
		this.stackedNode = stackedNode;
		this.primeNode = primeNode;
	}

	/**
	 * Returns starting time of request.
	 * 
	 * @return  Starting time of request.
	 */
	public int getTime() {
		
		return this.time;
	}

	/**
	 * Returns source of request.
	 * 
	 * @return  Source of request.
	 */
	public int getSource() {
		
		return this.source;
	}
	
	/**
	 * Returns target of request.
	 * 
	 * @return  Target of request.
	 */
	public int getTarget() {
		
		return this.target;
	}
	
	/**
	 * Returns data package.
	 * 
	 * @return  Data package.
	 */
	public int getData() {
		
		return this.data;
	}
	
	/**
	 * Returns node, where is data package saved.
	 * 
	 * @return  Node, where is data package saved.
	 */
	public Node getStackedNode() {
		
		return this.stackedNode;
	}

	/**
	 * Returns prime node of default dijkstra.
	 * 
	 * @return  Prime node of default dijkstra.
	 */
	public Node getPrimeNode() {
		
		return primeNode;
	}
}