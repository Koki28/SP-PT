package sp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Instance of class {@code Path} representing the shortest
 * path between selected nodes in computer web. 
 *
 * @author  Pavel Prùcha and Tomáš Slíva
 */

// koeficient
public class Path {

	/** Copy of the list of all nodes. */
	public ArrayList <Node> nodes;
	
	/** Copy of the list of all edges. */
	public ArrayList <Edge> edges;
	
	/** Set of explored nodes. */
	HashSet <Node> explored;
	
	/** Set of unexplored nodes. */
	HashSet <Node> unexplored;
	
	/** Map of ancestor nodes. */
	HashMap <Node, Node> ancestor;
	
	/** Map of distance between nodes. */
	HashMap <Node, Double> distance;
	
	/** Graph of computer web. */
	private Graph graph = DataInput.getGraph();

    /**
	 * Constructor creating path with using
	 * copy of all nodes and edges of computer web.
	 */
	public Path() {
		
		this.nodes = new ArrayList <Node>(graph.nodes);
		this.edges = new ArrayList <Edge>(graph.edges);
	}

	/**
	 * This method is examining start node and then
	 * finding the shortest path to the target node.
	 * 
	 * @param startNode  Examined node.
	 */
	public void examineNode(Node startNode) {
		
		 explored = new HashSet <Node>();
		 unexplored = new HashSet <Node>();
		 distance = new HashMap <Node, Double>();
		 ancestor = new HashMap <Node, Node>();
		 distance.put(startNode, 0.0);
		 unexplored.add(startNode);
		 
		 while(unexplored.size() > 0) {
		            
			 Node node = getMinimum(unexplored);
		     explored.add(node);
		     unexplored.remove(node);
		     findShortestPath(node);
		 } 
	}
	
	/**
	 * This method is finding the shortest path with using distances
	 * between target node and neighbours of selected node.
	 * 
	 * @param selectedNode  Selected node.
	 */
	public void findShortestPath(Node selectedNode) {
        
		ArrayList <Node> neighbourNodes = getNeighbours(selectedNode);
        
		for(Node target : neighbourNodes) {
           
			if(getShortestPath(target) > (getShortestPath(selectedNode) + getDistance(selectedNode, target))) {
                
            	distance.put(target, (getShortestPath(selectedNode) + getDistance(selectedNode, target)));
                ancestor.put(target, selectedNode);
                unexplored.add(target);
            }
        }
    }
	
	/**
	 * Returns distance between selected node and target node.
	 * 
	 * @param selectedNode  Selected node.
	 * @param target  Target node.
	 * 
	 * @return  Distance between selected node and target node.
	 */
	public double getDistance(Node selectedNode, Node target) {
        
		for (Edge edge : edges) {
            
			if (edge.getStartNode().equals(selectedNode) && edge.getTargetNode().equals(target)) {
                
				return (1 / edge.getTransmittanceDijkstra());
            }
        }
		
        throw new RuntimeException("Error.");
    }
	
	/**
	 * Returns the list of neighbours of selected node.
	 * 
	 * @param selectedNode  Selected node.
	 * 
	 * @return  List of neighbours of selected node.
	 */
	public ArrayList <Node> getNeighbours(Node selectedNode) {
		
        ArrayList <Node> neighbours = new ArrayList <Node>();
        
        for (Edge edge : edges) {
        	
            if (edge.getStartNode().equals(selectedNode) && !isCompleted(edge.getTargetNode())) {
               
            	neighbours.add(edge.getTargetNode());
            }
        }
        
        return neighbours;
    }
	
	/**
	 * Returns node with minimal distance.
	 * 
	 * @param unexploredNodes  Set of all unexplored nodes.
	 * 
	 * @return  Node minimal distance.
	 */
	public Node getMinimum(HashSet <Node> unexploredNodes) {
       
		Node minimalNode = null;
        
		for(Node selectedNode : unexploredNodes) {
            
			if (minimalNode == null) {
                
				minimalNode = selectedNode;
				
            } else {
                
            	if(getShortestPath(selectedNode) < getShortestPath(minimalNode)) {
            		
            		minimalNode = selectedNode;
                }
            }
        }
		
        return minimalNode;
    }
	
	/**
	 * Returns the shortest path to the target node.
	 * If the path doesn´t exist, returns max value.
	 * 
	 * @param target  Target node.
	 * 
	 * @return  Distance to the target node.
	 */
	public double getShortestPath(Node target) {
		
        if(distance.get(target) == null) {
        	
            return Double.MAX_VALUE;
            
        } else {
        	
            return distance.get(target);
        }
    }
	
	/**
	 * This method is checking if the selected
	 * node is already completed.
	 * 
	 * @param selectedNode  Selected node.
	 * 
	 * @return  True or false.
	 */
	public boolean isCompleted(Node selectedNode) {
        
		return explored.contains(selectedNode);
    }
	
	/**
	 * Return list of path of all nodes to the taget node.
	 * 
	 * @param targetNode Target node.
	 * 
	 * @return  Sorted list of all nodes to the target node.
	 */
	public LinkedList <Node> getPath(Node targetNode) {
        
		LinkedList <Node> path = new LinkedList <Node>();
        
		Node step = targetNode;
     
        if (ancestor.get(step) == null) {
            
        	return null;
        }
        
        path.add(step);
        
        while(ancestor.get(step) != null) {
        	
            step = ancestor.get(step);
            path.add(step);
        }
       
        Collections.reverse(path);
        
        return path;
    }
}