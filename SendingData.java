package sp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javafx.scene.control.TextArea;

/**
 * Instance of class {@code SendingData} representing
 * sending data packages between selected nodes.
 *
 * @author  Pavel Prùcha and Tomáš Slíva
 */
public class SendingData {

	private static Random r = new Random();

	/** Graph of computer web. */
	private static Graph graph = DataInput.getGraph();

	/** Ammount of lost data. */
	private static int dataLost = 0;

	/** List of all requests. */
	private static List <Simulation> requests = SimulationInput.getRequests();
	
	/**
	 * This method is loading all requests and simulate
	 * completing all requests according to time.
	 */
	public static void completeRequests() {
		
		int timer = 0; 
		
		Path path = new Path();
		
		while(!requests.isEmpty()) {
			
			for(int i = 0; i < requests.size(); i++) {
								
				int time = requests.get(i).getTime();
				
				if(time == timer) {
				
					Node sourceNode = graph.getNode(requests.get(i).getSource());
					Node targetNode = graph.getNode(requests.get(i).getTarget());
					Node stackedNode = requests.get(i).getStackedNode();
					Node primeNode = requests.get(i).getPrimeNode();
					int data = requests.get(i).getData();			
				
					System.out.println("\nMovement of data packages: " + sourceNode + " -> " + targetNode);
						
					try (BufferedWriter bw = new BufferedWriter(new FileWriter("simulation.txt", true))) {
					
						bw.newLine();
						bw.write("Movement of data packages: " + sourceNode + " -> " + targetNode);
						bw.newLine();
					
						path.examineNode(sourceNode);

						List<Node> dijkstra = path.getPath(targetNode);

						if(dijkstra == null) {

							System.out.println("Path doesn´t exist.");
					
							bw.newLine();
							bw.write("Path doesn´t exist.");
							
							return;
						}
					
						Node first;
						
						if (primeNode == null) {
							
							first = dijkstra.get(0);
							
						} else {
							
							first = primeNode;
						}
						
						sendData(timer, data, dijkstra, stackedNode, primeNode, first);
						requests.set(i, null);
						
						bw.close();
						
					} catch (IOException e) {
						
						e.printStackTrace();
					}
				}
			}
			
			for(int i = 0; i < requests.size(); i++) {
				
				if(requests.get(i) == null) {
					
					requests.remove(i);
					i--;
				}
			}
			
			timer++;
		}
	}
	
	/**
	 * This method is loading all requests and simulate
	 * completing all requests according to time.
	 * 
	 * @param x  If false print into console, else into GUI.
	 * @param textArea  TextArea of GUI.
	 */
	public static void completeRequests(boolean x, TextArea textArea) {
		
		int timer = 0; 
		
		Path path = new Path();
		
		while(!requests.isEmpty()) {
			
			for(int i = 0; i < requests.size(); i++) {
								
				int time = requests.get(i).getTime();
				
				if(time == timer) {
				
					Node sourceNode = graph.getNode(requests.get(i).getSource());
					Node targetNode = graph.getNode(requests.get(i).getTarget());
					Node stackedNode = requests.get(i).getStackedNode();
					Node primeNode = requests.get(i).getPrimeNode();
					int data = requests.get(i).getData();			
				
					textArea.appendText("\n\nMovement of data packages: " + sourceNode + " -> " + targetNode);
				
					try (BufferedWriter bw = new BufferedWriter(new FileWriter("simulation.txt", true))) {
					
						bw.newLine();
						bw.write("Movement of data packages: " + sourceNode + " -> " + targetNode);
						bw.newLine();
					
						path.examineNode(sourceNode);

						List<Node> dijkstra = path.getPath(targetNode);

						if(dijkstra == null) {

							textArea.appendText("Path doesn´t exist.");
					
							bw.newLine();
							bw.write("Path doesn´t exist.");
							
							return;
						}
					
						Node firstNode;
						
						if (primeNode == null) {
							
							firstNode = dijkstra.get(0);
							
						} else {
							
							firstNode = primeNode;
						}
						
						sendData(timer, data, dijkstra, stackedNode, primeNode, x, textArea, firstNode);
						requests.set(i, null);
						
						bw.close();
						
					} catch (IOException e) {
						
						e.printStackTrace();
					}
				}
			}
			
			for(int i = 0; i < requests.size(); i++) {
				
				if(requests.get(i) == null) {
					
					requests.remove(i);
					i--;
				}
			}
			
			timer++;
		}
	}
	
	/**
	 * This method is sending data packages
	 * through the shortest path to the target node.
	 * 
	 * @param time  Starting time of request.
	 * @param data  Data package.
	 * @param dijkstra  Sorted list of all nodes to the target node.
	 * @param stackedNode  Node, where is data package saved.
	 * @param primeNode  Prime node of default dijkstra.
	 * @param firstNode  First node of dijkstra.
	 */
public static void sendData(int time, int data, List <Node> dijkstra, Node stackedNode, Node prime, Node first) {
		
		int loss = 0;
		int sent = 0;
		int second = time;
		int packageData = data;
	
		Node last = dijkstra.get(dijkstra.size() - 1);
		Node one = dijkstra.get(0); 
		Node two = dijkstra.get(1); 
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("simulation.txt", true))) {
				
			System.out.println("\n--- second " + second + " ---");
			System.out.println("Sending data about size <" + packageData + "> from node " +  one.getId() + " to node " + two.getId() + ": ");

			bw.newLine();
			bw.write("--- second " + second + " ---");
			bw.newLine();
			bw.write("Sending data about size <" + packageData + "> from node " +  one.getId() + " to node " + two.getId() + ": ");
			bw.newLine();
			
			Edge edge = graph.getEdge(one, two);
			int transmittance = edge.getTransmittance(); 	
			double faulting = edge.getFaulting(); 

			System.out.println("transmittance " + one.getId() + " -> " + two.getId() + ": " + transmittance);
			System.out.println("faulting " + one.getId() + " -> " + two.getId() + ": " + faulting);
						
			bw.write("transmittance " + one.getId() + " -> " + two.getId() + ": " + transmittance);
			bw.newLine();
			bw.write("faulting " + one.getId() + " -> " + two.getId() + ": " + faulting);
			bw.newLine();

			double rand = r.nextDouble();
			
			if(transmittance > packageData) {
					
				if(((double) packageData / (double) transmittance) > faulting && rand < 0.5) {
							
					loss = (int) Math.ceil(packageData / 2.0);
							
					System.out.println("- Overload! -  Half ammount of data must be sent again (" + loss + ").");
	
					bw.write("- Overload! -  Half ammount of data must be sent again (" + loss + ").");
					bw.newLine();
					bw.newLine();
							
					dataLost += loss;
					requests.add(new Simulation(second + 1, first.getId(), last.getId(), loss, null, first));
						
					deleteStack(stackedNode, loss);
						
					bw.write("--- second " + second + " ---");
					bw.newLine();
							
					packageData = packageData - loss; 
				}

				System.out.println("From node " + one.getId() + " to node " + two.getId() + " was sent " + packageData + " amount of data.");

				bw.write("From node " + one.getId() + " to node " + two.getId() + " was sent " + packageData + " ammount of data.");
				bw.newLine();
					
				endOrAgain(two, last, second, packageData, first, false, null);
				deleteStack(stackedNode, packageData);
				
			} else {
					
				int remainingData = packageData - transmittance;
				sent = transmittance;

				if (faulting != 1.0 && rand < 0.5) {
								
					loss = (int) Math.ceil(transmittance / 2.0); 
			
					System.out.println("- Overload! -  Half ammount of data must be sent again (" + loss + ").");
	
					bw.write("- Overload! -  Half ammount of data must be sent again (" + loss + ").");
					bw.newLine();				
					bw.newLine();
							
					dataLost += loss;
					requests.add(new Simulation(second + 1, first.getId(), last.getId(), loss, null, first));
						
					deleteStack(stackedNode, loss);
					
					System.out.println("\n--- second " + second + " ---");
	
					bw.write("--- second " + second + " ---");
					bw.newLine();
							
					sent = transmittance - loss;
				}
					
				System.out.println("From node " + one.getId() + " to node " + two.getId() + " was sent " + sent + " ammount of data.");
					
				bw.write("From node " + one.getId() + " to node " + two.getId() + " was sent " + sent + " ammount of data.");
				bw.newLine();
					
				endOrAgain(two, last, second, packageData, first, false, null);
				deleteStack(stackedNode, packageData);
					
				if (!one.stack.addMemory(remainingData)) {
						
					System.out.println("Memory of node " + one.getId() + " overflowed! Data must be sent again (" +  remainingData + ").");
									
					bw.write("Memory of node " + one.getId() + " overflowed! Data must be sent again (" +  remainingData + ").");
					bw.newLine();
						
					dataLost += remainingData;
					requests.add(new Simulation(second + 1, first.getId(), last.getId(), remainingData, null, first));
						
					deleteStack(stackedNode, remainingData);
											
				} else {
					
					System.out.println(remainingData + " ammount of data was saved to memory stack of node " + one.getId() + ".");
											
					bw.write(remainingData + " ammount of data was saved to memory stack of node " + one.getId() + ".");
					bw.newLine();
						
					requests.add(new Simulation(second + 1, one.getId(), last.getId(), remainingData, one, first));
						
					deleteStack(stackedNode, remainingData);
			  }
		}
		
		bw.close();
		
		} catch (Exception e) {

			System.err.println("Failed! Data aren´t recorded in the file.");
		} 
	}
	
	/**
	 * This method is sending data packages
	 * through the shortest path to the target node.
	 * 
	 * @param time  Starting time of request.
	 * @param data  Data package.
	 * @param dijkstra  Sorted list of all nodes to the target node.
	 * @param stackedNode  Node, where is data package saved.
	 * @param prime  Prime node of default dijkstra.
	 * @param x  If false print into console, else into GUI.
	 * @param textArea  TextArea of GUI.
	 * @param first  First node of dijkstra.
	 */
	public static void sendData(int time, int data, List <Node> dijkstra, Node stackedNode, Node prime, boolean x, TextArea textArea, Node first) {
		
		int loss = 0;
		int sent = 0;
		int second = time;
		int packageData = data;
		double rand;
	
		Node last = dijkstra.get(dijkstra.size() - 1);
		Node one = dijkstra.get(0); 
		Node two = dijkstra.get(1);
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("simulation.txt", true))) {
				
			textArea.appendText("\n--- second " + second + " ---\n");
			textArea.appendText("Sending data about size <" + packageData + "> from node " +  one.getId() + " to node " + two.getId() + ": \n");
				
			bw.newLine();
			bw.write("--- second " + second + " ---");
			bw.newLine();
			bw.write("Sending data about size <" + packageData + "> from node " +  one.getId() + " to node " + two.getId() + ": ");
			bw.newLine();

			Edge edge = graph.getEdge(one, two);
			int transmittance = edge.getTransmittance(); 	
			double faulting = edge.getFaulting();

			textArea.appendText("transmittance " + one.getId() + " -> " + two.getId() + ": " + transmittance + "\n");
			textArea.appendText("faulting " + one.getId() + " -> " + two.getId() + ": " + faulting + "\n");
				
			bw.write("transmittance " + one.getId() + " -> " + two.getId() + ": " + transmittance);
			bw.newLine();
			bw.write("faulting " + one.getId() + " -> " + two.getId() + ": " + faulting);
			bw.newLine();

			rand = r.nextDouble();
			
			if(transmittance > packageData) {
					
				if(((double) packageData / (double) transmittance) > faulting && rand < 0.5) {
							
					loss = (int) Math.ceil(packageData / 2.0);
						
					textArea.appendText("- Overload! -  Half ammount of data must be sent again (" + loss + ").\n");
		
					bw.write("- Overload! -  Half ammount of data must be sent again (" + loss + ").");
					bw.newLine();
					bw.newLine();
							
					dataLost += loss;
					requests.add(new Simulation(second + 1, first.getId(), last.getId(), loss, null, first));
						
					deleteStack(stackedNode, loss);
					
					bw.write("--- second " + second + " ---");
					bw.newLine();
							
					packageData = packageData - loss;
				}

				textArea.appendText("From node " + one.getId() + " to node " + two.getId() + " was sent " + packageData + " amount of data.\n");
					
				bw.write("From node " + one.getId() + " to node " + two.getId() + " was sent " + packageData + " ammount of data.");
				bw.newLine();
					
				endOrAgain(two, last, second, packageData, first, x, textArea);
				deleteStack(stackedNode, packageData);
		
			} else {
					
				int remainingData = packageData - transmittance;
				sent = transmittance;

				if (faulting != 1.0 && rand < 0.5) {
								
					loss = (int) Math.ceil(transmittance / 2.0); 
							
					textArea.appendText("- Overload! -  Half ammount of data must be sent again (" + loss + ").");
					
					bw.write("- Overload! -  Half ammount of data must be sent again (" + loss + ").");
					bw.newLine();				
					bw.newLine();
							
					dataLost += loss;
					requests.add(new Simulation(second + 1, first.getId(), last.getId(), loss, null, first));
						
					deleteStack(stackedNode, loss);
					
					textArea.appendText("\n--- second " + second + " ---\n");
							
					bw.write("--- second " + second + " ---");
					bw.newLine();
							
					sent = transmittance - loss;
				}
					
				textArea.appendText("From node " + one.getId() + " to node " + two.getId() + " was sent " + sent + " ammount of data.\n");
								
				bw.write("From node " + one.getId() + " to node " + two.getId() + " was sent " + sent + " ammount of data.");
				bw.newLine();
					
				endOrAgain(two, last, second, packageData, first, x, textArea);
				deleteStack(stackedNode, packageData);
				
				if (!one.stack.addMemory(remainingData)) {
						
					textArea.appendText("Memory of node " + one.getId() + " overflowed! Data must be sent again (" +  remainingData + ").\n");
										
					bw.write("Memory of node " + one.getId() + " overflowed! Data must be sent again (" +  remainingData + ").");
					bw.newLine();
						
					dataLost += remainingData;
					requests.add(new Simulation(second + 1, first.getId(), last.getId(), remainingData, null, first));
						
					deleteStack(stackedNode, remainingData);
										
				} else {
					
					textArea.appendText(remainingData + " ammount of data was saved to memory stack of node " + one.getId() + ".\n");
										
					bw.write(remainingData + " ammount of data was saved to memory stack of node " + one.getId() + ".");
					bw.newLine();
						
					requests.add(new Simulation(second + 1, one.getId(), last.getId(), remainingData, one, first));
						
					deleteStack(stackedNode, remainingData);
			  }
		}
		
		bw.close();
		
		} catch (Exception e) {

			textArea.appendText("Failed! Data aren´t recorded in the file.\n");
		} 
	} 

	/**
	 * This method is writing total sum of faulting.
	 * 
	 * @param x  If false print into console, else into GUI.
	 * @param textArea  TextArea of GUI.
	 * 
	 * @return  Total sum of faulting.
	 */
	public static int writeFaulting(boolean x, TextArea textArea) {
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("simulation.txt", true))) {
			
			if(!x) {
				
				System.out.println("\nFaulting this solution is " + dataLost + ".");
				System.out.println("\nSimulation finished !!!");
				System.out.println();
				
			} else {
				
				textArea.appendText("\nFaulting this solution is " + dataLost + ". \n");
				textArea.appendText("\nSimulation finished !!! \n");
			}
			
			bw.newLine();
			bw.write("Faulting this solution is " + dataLost + ".");
			bw.newLine();
			bw.newLine();
			bw.write("Simulation finished !!!");
			bw.newLine();
			bw.newLine();
			bw.close();
		
		} catch (Exception e) {

			if(!x) {
				
				System.err.println("Failed! Data aren´t recorded in the file.");

			} else {
				
				textArea.appendText("Failed! Data aren´t recorded in the file.\n");
			}			
		}
		
		return dataLost;
	} 
	
	/**
	 * This method is deciding if the request is completed or not. 
	 * If not, method is founding new request.
	 * 
	 * @param two  Second node of dijkstra.
	 * @param last  Last node of dijkstra.
	 * @param second  Starting time of request.
	 * @param packageData  Data package.
	 * @param first  First node of dijkstra.
	 * @param x  If false print into console, else into GUI.
	 * @param textArea  TextArea of GUI.
	 */
	public static void endOrAgain(Node two, Node last, int second, int packageData, Node first, boolean x, TextArea textArea) {
		
		if (two.getId() != last.getId()) {
			
			requests.add(new Simulation(second + 1, two.getId(), last.getId(), packageData, null, first));

		} else {
			
			try (BufferedWriter bw = new BufferedWriter(new FileWriter("simulation.txt", true))) {
			
				if(!x) {
				
					System.out.println("--- Data successfully sent to the target node (path " + first.getId() + " -> " + last.getId() + "). ---");

				} else {
				
					textArea.appendText("--- Data successfully sent to the target node (path " + first.getId() + " -> " + last.getId() + "). ---\n");
				} 
									
			bw.write("--- Data successfully sent to the target node (path " + first.getId() + " -> " + last.getId() + "). ---");
			bw.newLine();
			
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * This method is deleting data from memory stack of selected node.
	 * 
	 * @param stackedNode  Node, where is data package saved.
	 * @param data  Data package.
	 */
	public static void deleteStack(Node stackedNode, int data) {
		
		if(stackedNode != null) {
			
			stackedNode.stack.deleteData(data);
		}
	}

	/**
	 * udìlat
	 */
	public static void watchNode() {
		
	}
}