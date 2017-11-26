package sp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

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
	private static ArrayList <Simulation> requests = SimulationInput.getRequests();
	
	/**
	 * Empty constructor of class SendingData.
	 */
	public SendingData() {
	}
	
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
					bw.close();
					
				} catch (IOException e) {
					
					e.printStackTrace();
				}
					
				path.examineNode(sourceNode);

				LinkedList <Node> dijkstra = path.getPath(targetNode);

				if(dijkstra == null) {

					System.out.println("Path doesn´t exist.");
					return;
				}
					
				sendData(timer, data, dijkstra, stackedNode, primeNode);
				requests.set(i, null);
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
	 * @param prime  Prime node of default dijkstra.
	 */
	public static void sendData(int time, int data, LinkedList <Node> dijkstra, Node stackedNode, Node prime) {
		
		int loss = 0;
		int sent = 0;
		int second = time;
		double rand;
		
		Node first;
		
		if (prime == null) {
			
			first = dijkstra.get(0);
			
		} else {
			
			first = prime;
		}

		Node last = dijkstra.get(dijkstra.size() - 1);
		Node one = dijkstra.get(0); 
		Node two = dijkstra.get(1);
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("simulation.txt", true))) {
				
				System.out.println("\n--- second " + second + " ---");
				System.out.println("Sending data about size <" + data + "> from node " +  one.getId() + " to node " + two.getId() + ": ");
				
				bw.newLine();
				bw.write("--- second " + second + " ---");
				bw.newLine();
				bw.write("Sending data about size <" + data + "> from node " +  one.getId() + " to node " + two.getId() + ": ");
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

				if(transmittance > data) {
					
					rand = r.nextDouble();
					
					if(((double) data / (double) transmittance) > faulting) {
						
						if (rand < 0.5) {
							
							loss = (int) Math.ceil(data / 2.0);
							
							System.out.println("- Overload! -  Half ammount of data must be sent again (" + loss + ").");
							
							bw.write("- Overload! -  Half ammount of data must be sent again (" + loss + ").");
							bw.newLine();
							bw.newLine();
							
							dataLost += loss;
							requests.add(new Simulation(second + 1, first.getId(), last.getId(), loss, null, first));
							
							if(stackedNode != null) {
								
								stackedNode.stack.deleteData(loss);
							}

							System.out.println("\n--- second " + second + " ---");
							
							bw.write("--- second " + second + " ---");
							bw.newLine();
							
							data = data - loss;
						}
					}

					System.out.println("From node " + one.getId() + " to node " + two.getId() + " was sent " + data + " amount of data.");
					
					bw.write("From node " + one.getId() + " to node " + two.getId() + " was sent " + data + " ammount of data.");
					bw.newLine();
					
					if (two.getId() != last.getId()) {
					
						requests.add(new Simulation(second + 1, two.getId(), last.getId(), data, null, first));
					
					} else {
						
						System.out.println("--- Data successfully sent to the target node (path " + first.getId() + " -> " + last.getId() + "). ---");
						
						bw.write("--- Data successfully sent to the target node (path " + first.getId() + " -> " + last.getId() + "). ---");
						bw.newLine();
					}
					
					if(stackedNode != null) {
						
						stackedNode.stack.deleteData(data);
					}
					
				} else {
					
					int remainingData = data - transmittance;
					sent = transmittance;
					rand = r.nextDouble();

					if (faulting != 1.0) {
						
						if (rand < 0.5) {
							
							loss = (int) Math.ceil(transmittance / 2.0); 
							
							System.out.println("- Overload! -  Half ammount of data must be sent again (" + loss + ").");
							
							bw.write("- Overload! -  Half ammount of data must be sent again (" + loss + ").");
							bw.newLine();
							bw.newLine();

							
							dataLost += loss;
							requests.add(new Simulation(second + 1, first.getId(), last.getId(), loss, null, first));
							
							if(stackedNode != null) {
								
								stackedNode.stack.deleteData(loss);
							}

							System.out.println("\n--- second " + second + " ---");
							
							bw.write("--- second " + second + " ---");
							bw.newLine();
							
							sent = transmittance - loss;
						}
					}
					
					System.out.println("From node " + one.getId() + " to node " + two.getId() + " was sent " + sent + " ammount of data.");
					
					bw.write("From node " + one.getId() + " to node " + two.getId() + " was sent " + sent + " ammount of data.");
					bw.newLine();
					
					if (two.getId() != last.getId()) {
						
						requests.add(new Simulation(second + 1, two.getId(), last.getId(), data, null, first));

					} else {
						
						System.out.println("--- Data successfully sent to the target node (path " + first.getId() + " -> " + last.getId() + "). ---");
						
						bw.write("--- Data successfully sent to the target node (path " + first.getId() + " -> " + last.getId() + "). ---");
						bw.newLine();					
					}
					
					if(stackedNode != null) {
						
						stackedNode.stack.deleteData(data);
					}
					
					if (!one.stack.addMemory(remainingData)) {
						
						System.out.println("Memory of node " + one.getId() + " overflowed! Data must be sent again (" +  remainingData + ").");
						
						bw.write("Memory of node " + one.getId() + " overflowed! Data must be sent again (" +  remainingData + ").");
						bw.newLine();
						
						dataLost += remainingData;
						requests.add(new Simulation(second + 1, first.getId(), last.getId(), remainingData, null, first));
						
						if(stackedNode != null) {
							
							stackedNode.stack.deleteData(remainingData);
						}
												
					} else {
						
						System.out.println(remainingData + " ammount of data was saved to memory stack of node " + one.getId() + ".");
						
						bw.write(remainingData + " ammount of data was saved to memory stack of node " + one.getId() + ".");
						bw.newLine();
						
						requests.add(new Simulation(second + 1, one.getId(), last.getId(), remainingData, one, first));
						
						if(stackedNode != null) {
							
							stackedNode.stack.deleteData(remainingData);
						}
				  }
			}
		
		bw.close();
		
		} catch (Exception e) {

				System.err.println("Failed! Data aren´t recorded in the file.");
		} 
	}

	public static int writeFaulting() {
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("simulation.txt", true))) {
			
			System.out.println("\nFaulting this solution is " + dataLost + ".");
			
			bw.newLine();
			bw.write("Faulting this solution is " + dataLost + ".");
			bw.close();
		
		} catch (Exception e) {

			System.err.println("Failed! Data aren´t recorded in the file.");
		}
		
		return dataLost;
	}
}