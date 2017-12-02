package sp;

import java.util.ArrayList;
import java.util.List;

/**
 * Instance of class {@code Node} representing nodes of computer web.
 *
 * @author  Pavel Prùcha and Tomáš Slíva
 */
public class Node {

	/** Node ID. */
	public int id;
	
	/** Node memory stack. */
    public Stack stack;
    
    /** List of adjacent nodes. */
	public List <Node> neighbours;
	
   /**
    * Constructor creating node of computer web.
    *
    * @param id  Node ID.
    */
	public Node(int id) {
		
		this.id = id;
		this.neighbours = new ArrayList <Node>();
		this.stack = new Stack();
	}
	
   /**
	* Returns node ID.
	* 
	* @return  Node ID.
	*/
	public int getId() {
		
		return this.id;
	}
	
	/**
	* Converts instance to string containing node ID.
	* 
	* @return  String representation of given node.
	*/
	@Override
	public String toString() {
		
		String node = "ID " + id;
		
		return node;
	}
}