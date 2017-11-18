package sp;

//import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class PrechodDat {

	private static Random r = new Random();

	//public ArrayList<LinkedList> dijstri = NacitaniSimulace.getDijkstri();
	Graf graf = NacitaniVstupnihoSouboru.getGraf();
	
	int ztratovost = 0;
	
	//int cas;
	//int data;
	//LinkedList<Vrchol> dijkstra;
	
	public PrechodDat() {

	//	this.cas = cas;
	//	this.data = data;
	//	this.dijkstra = dijkstra;
	}
	
	public void posliData(int cas, int data, LinkedList<Vrchol> dijkstra) {
		int ubytek = 0;
		int poslano = 0;
		int vterina = cas;
		double rand;
		
		for(int i = 0; i < dijkstra.size()-1; i++) {
			vterina++;
			Vrchol prvni = dijkstra.get(i); 
			Vrchol druhy = dijkstra.get(i+1); 
			System.out.println("\n--- vterina " + vterina + " ---");
			System.out.println("Posílám data o velikosti <" + data + "> z vrcholu " +  prvni.getId() + " do vrcholu " + druhy.getId() + ": ");
			
			Hrana hrana = graf.getHrana(prvni, druhy);
			int propustnostHrany = hrana.getPropustnost(); 	
			double chybovostHrany = hrana.getChybovost();
			
			System.out.println("propustnost " + prvni.getId() + "->" + druhy.getId() + ": " + propustnostHrany);
			System.out.println("chybovost " + prvni.getId() + "->" + druhy.getId() + ": " + chybovostHrany);
			
			
			if(propustnostHrany > data) {
				rand = r.nextDouble();
				
				System.out.println(rand);
				
				if (((double)data / (double)propustnostHrany) > chybovostHrany) 
					if (rand < 0.5) {
						ubytek = (int)Math.ceil(data/2.0);
						System.out.println("-Pøetížení!-  Pùlka dat se musí poslat znovu (" + ubytek + ").");
						ztratovost += ubytek;
						posliData(vterina, ubytek, dijkstra);
						
						System.out.println("\n--- vterina " + vterina + " ---");
						data = data - ubytek;
						
					}
				
				System.out.println("Z vrcholu " + prvni.getId() + " do vrcholu " + druhy.getId() + " bylo posláno " + data + " dat.");
				//poslat data dal
				
			}
			
			else{
				int pozastavenaData = data - propustnostHrany;
				poslano = propustnostHrany;
			 	rand = r.nextDouble();
			 	
			 	System.out.println(rand);
			 	if (chybovostHrany != 1.0)
				if (rand < 0.5) {
					ubytek = (int)Math.ceil(propustnostHrany/2.0); 
					System.out.println("-Pøetížení!-  Pùlka dat se musí poslat znovu (" + ubytek + ").");
					ztratovost += ubytek;
					posliData(vterina, ubytek, dijkstra);
					
					System.out.println("\n--- vterina " + vterina + " ---");
					poslano = propustnostHrany - ubytek;
				}
				System.out.println("Z vrcholu " + prvni.getId() + " do vrcholu " + druhy.getId() + " bylo posláno " + poslano + " dat.");
				//poslat data
				if (!prvni.stack.pridatPamet(pozastavenaData)) {
					System.out.println("Pamì vrcholu " + prvni.getId() + " pøetekla! Data se musí poslat znovu (" +  pozastavenaData + ").");
					posliData(vterina, pozastavenaData, dijkstra);
					break;
				}
				else
				System.out.println(pozastavenaData + " dat bylo uloženo do stacku vrcholu " + prvni.getId() + ".");
				
				while(pozastavenaData != 0) {
				vterina++;
				System.out.println("\n--- vterina " + vterina + " ---");
				
				if (propustnostHrany > pozastavenaData) {
					poslano = pozastavenaData;
					rand = r.nextDouble();
					
					System.out.println(rand);
					
					if (((double)pozastavenaData / (double)propustnostHrany) > chybovostHrany) 
					if (rand < 0.5) {
						ubytek = (int)Math.ceil(pozastavenaData/2.0); 
						System.out.println("-Pøetížení!-  Pùlka dat se musí poslat znovu (" + ubytek + ").");
						ztratovost += ubytek;
						posliData(vterina, ubytek, dijkstra);
						
						System.out.println("\n--- vterina " + vterina + " ---");
						poslano = pozastavenaData - ubytek;		
					}
					
					System.out.println("Z vrcholu " + prvni.getId() + " do vrcholu " + druhy.getId() + " bylo posláno " + poslano + " dat.");
					//poslat data
					prvni.stack.smazatData(pozastavenaData);
					System.out.println(pozastavenaData + " dat bylo smazáno ze stacku vrcholu " + prvni.getId() + ".");
					break;
				}
				
				rand = r.nextDouble();
				
				System.out.println(rand);
				
				poslano = propustnostHrany;
				if (chybovostHrany != 1.0)
				if (rand < 0.5) {
					ubytek = (int)Math.ceil(propustnostHrany/2.0); 
					System.out.println("-Pøetížení!-  Pùlka dat se musí poslat znovu (" + ubytek + ").");
					ztratovost += ubytek;
					posliData(vterina, ubytek, dijkstra);
					
					System.out.println("\n--- vterina " + vterina + " ---");
					poslano = propustnostHrany - ubytek;	
				}

				System.out.println("Z vrcholu " + prvni.getId() + " do vrcholu " + druhy.getId() + " boly posláno " + poslano + " dat.");
				//poslat data
				prvni.stack.smazatData(propustnostHrany);
				System.out.println(propustnostHrany + " dat bylo smazáno ze stacku vrcholu " + prvni.getId() + ".");
				pozastavenaData -= propustnostHrany;	
				}
				
			}
			
		}
	}

	public int vypisZtratovost() {
		System.out.println("\nZtrátovost je " + ztratovost);
		return ztratovost;
	}
	
	
}
