package sp;

import java.io.BufferedWriter;
import java.io.FileWriter;
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
	Graph graph = DataInput.getGraph();

	/** Ammount of lost data. */
	private static int dataLost = 0;

	/**
	 * Constructor creating sending data packages between nodes.
	 */
	public SendingData() {
		
	}
	
	/**
	 * This method is sending data packages
	 * through the shortest path to the target node.
	 * 
	 * @param time  Starting time of request.
	 * @param data  Data package.
	 * @param dijkstra  Sorted list of all nodes to the target node.
	 */
	public void sendData(int time, int data, LinkedList <Node> dijkstra) {
		
		int loss = 0;
		int sent = 0;
		int second = time;
		double rand;
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("simulation.txt", true))) {

			for(int i = 0; i < dijkstra.size() - 1; i++) {
				
				second++;
				
				Node one = dijkstra.get(i); 
				Node two = dijkstra.get(i + 1); 
				
				System.out.println("\n--- second " + second + " ---");
				System.out.println("Sending data about size <" + data + "> from node " +  one.getId() + " to node " + two.getId() + ": ");
				
				bw.newLine();
				bw.newLine();
				bw.write("--- second " + second + " ---");
				bw.newLine();
				bw.write("Sending data about size <" + data + "> from node " +  one.getId() + " to node " + two.getId() + ": ");
				bw.newLine();

				Edge edge = graph.getEdge(one, two);
				int transmittance = edge.getTransmittance(); 	
				double faulting = edge.getFaulting();

				System.out.println("transmittance " + one.getId() + "->" + two.getId() + ": " + transmittance);
				System.out.println("faulting " + one.getId() + "->" + two.getId() + ": " + faulting);
				
				bw.write("transmittance " + one.getId() + "->" + two.getId() + ": " + transmittance);
				bw.newLine();
				bw.write("faulting " + one.getId() + "->" + two.getId() + ": " + faulting);
				bw.newLine();

				if(transmittance > data) {
					
					rand = r.nextDouble();
					
					if(((double) data / (double) transmittance) > faulting) {
						
						if (rand < 0.5) {
							
							loss = (int) Math.ceil(data / 2.0);
							
							System.out.println("- Overload! -  Half ammount of data must be sent again (" + loss + ").");
							
							bw.write("- Overload! -  Half ammount of data must be sent again (" + loss + ").");
							bw.newLine();
							
							dataLost += loss;
							sendData(second, loss, dijkstra);

							System.out.println("\n--- second " + second + " ---");
							
							bw.write("--- second " + second + " ---");
							bw.newLine();
							bw.newLine();
							
							data = data - loss;
						}
					}

					System.out.println("From node " + one.getId() + " to node " + two.getId() + " was sent " + data + " amount of data.");
					
					bw.write("From node " + one.getId() + " to node " + two.getId() + " was sent " + data + " ammount of data.");
					bw.newLine();
					
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
							
							dataLost += loss;
							sendData(second, loss, dijkstra);

							System.out.println("\n--- second " + second + " ---");
							
							bw.write("--- second " + second + " ---");
							bw.newLine();
							bw.newLine();
							
							sent = transmittance - loss;
						}
					}
					
					System.out.println("From node " + one.getId() + " to node " + two.getId() + " was sent " + sent + " ammount of data.");
					
					bw.write("From node " + one.getId() + " to node " + two.getId() + " was sent " + sent + " ammount of data.");
					bw.newLine();
					
					if (!one.stack.addMemory(remainingData)) {
						
						System.out.println("Memory of node " + one.getId() + " overflowed! Data must be sent again (" +  remainingData + ").");
						
						bw.write("Memory of node " + one.getId() + " overflowed! Data must be sent again (" +  remainingData + ").");
						bw.newLine();
						
						dataLost += remainingData;
						sendData(second, remainingData, dijkstra);
						break;
						
					} else {
						
						System.out.println(remainingData + " ammount of data was saved to memory stack of node " + one.getId() + ".");
						
						bw.write(remainingData + " ammount of data was saved to memory stack of node " + one.getId() + ".");
						bw.newLine();
					}
					
					while(remainingData != 0) {
						
						second++;
						
						System.out.println("\n--- second " + second + " ---");
						
						bw.write("--- second " + second + " ---");
						bw.newLine();
						bw.newLine();

						if (transmittance > remainingData) {
							
							sent = remainingData;
							rand = r.nextDouble();

							if (((double) remainingData / (double) transmittance) > faulting) {
								
								if (rand < 0.5) {
									
									loss = (int) Math.ceil(remainingData / 2.0); 
									//
									System.out.println("- Overload! -  Half ammount of data must be sent again (" + loss + ").");
									
									bw.write("- Overload! -  Half ammount of data must be sent again (" + loss + ").");
									bw.newLine();
									
									dataLost += loss;
									sendData(second, loss, dijkstra);

									System.out.println("\n--- second " + second + " ---");
									
									bw.write("--- second " + second + " ---");
									bw.newLine();
									bw.newLine();
									
									sent = remainingData - loss;		
								}
							}
							System.out.println("From node " + one.getId() + " to node " + two.getId() + " was sent " + sent + " ammount of data.");
							bw.write("From node " + one.getId() + " to node " + two.getId() + " was sent " + sent + " ammount of data.");
							bw.newLine();
							//poslat data
							
							one.stack.deleteData(remainingData);
							
							System.out.println(remainingData + " ammount of data was deleted from memory stack of node " + one.getId() + ".");
							
							bw.write(remainingData + " ammount of data was deleted from memory stack of node " + one.getId() + ".");
							bw.newLine();
							
							break;
						}

						rand = r.nextDouble();
						sent = transmittance;
						
						if(faulting != 1.0) {
							
							if(rand < 0.5) {
								
								loss = (int) Math.ceil(transmittance / 2.0); 
								
								System.out.println("- Overload! -  Half ammount of data must be sent again (" + loss + ").");
								
								bw.write("- Overload! -  Half ammount of data must be sent again (" + loss + ").");
								
								bw.newLine();
								dataLost += loss;
								sendData(second, loss, dijkstra);

								System.out.println("\n--- second " + second + " ---");
								
								bw.write("--- second " + second + " ---");
								bw.newLine();
								bw.newLine();
								
								sent = transmittance - loss;	
							}

						System.out.println("From node " + one.getId() + " to node " + two.getId() + " was sent " + sent + " ammount of data.");
						
						bw.write("From node " + one.getId() + " to node " + two.getId() + " was sent " + sent + " ammount of data.");
						bw.newLine();
						
						one.stack.deleteData(transmittance);
						
						System.out.println(transmittance + " ammount of data was deleted from memory stack of node " + one.getId() + ".");
						
						bw.write(transmittance + " ammount of data was deleted from memory stack of node " + one.getId() + ".");
						bw.newLine();
						
						remainingData -= transmittance;
						}
					}
				}
			}
			
			bw.close();
		
		} catch (Exception e) {

			System.err.println("Failed! Data aren´t recorded in the file.");
		}
	}

	public int writeFaulting() {
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("simulation.txt", true))) {
			
			System.out.println("\nFaulting is " + dataLost);
			
			bw.newLine();
			bw.write("Faluting is " + dataLost);
			bw.newLine();
			bw.newLine();
			bw.close();
		
		} catch (Exception e) {

			System.err.println("Failed! Data aren´t recorded in the file.");
		}
		
		return dataLost;
	}
}