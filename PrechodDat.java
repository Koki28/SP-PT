package sp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.Random;

public class PrechodDat {

	private static Random r = new Random();

	Graf graf = NacitaniVstupnihoSouboru.getGraf();


	int ztratovost = 0;

	public PrechodDat() {
	}

	public void posliData(int cas, int data, LinkedList<Vrchol> dijkstra) {
		int ubytek = 0;
		int poslano = 0;
		int vterina = cas;
		double rand;
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("prubehSimulace.txt", true))) {


			for(int i = 0; i < dijkstra.size()-1; i++) {
				vterina++;
				Vrchol prvni = dijkstra.get(i); 
				Vrchol druhy = dijkstra.get(i+1); 
				System.out.println("\n--- vterina " + vterina + " ---");
				System.out.println("Pos�l�m data o velikosti <" + data + "> z vrcholu " +  prvni.getId() + " do vrcholu " + druhy.getId() + ": ");
				bw.newLine();
				bw.newLine();
				bw.write("--- vterina " + vterina + " ---");
				bw.newLine();
				bw.write("Pos�l�m data o velikosti <" + data + "> z vrcholu " +  prvni.getId() + " do vrcholu " + druhy.getId() + ": ");
				bw.newLine();

				Hrana hrana = graf.getHrana(prvni, druhy);
				int propustnostHrany = hrana.getPropustnost(); 	
				double chybovostHrany = hrana.getChybovost();

				System.out.println("propustnost " + prvni.getId() + "->" + druhy.getId() + ": " + propustnostHrany);
				System.out.println("chybovost " + prvni.getId() + "->" + druhy.getId() + ": " + chybovostHrany);
				bw.write("propustnost " + prvni.getId() + "->" + druhy.getId() + ": " + propustnostHrany);
				bw.newLine();
				bw.write("chybovost " + prvni.getId() + "->" + druhy.getId() + ": " + chybovostHrany);
				bw.newLine();


				if(propustnostHrany > data) {
					rand = r.nextDouble();
					if (((double)data / (double)propustnostHrany) > chybovostHrany) 
						if (rand < 0.5) {
							ubytek = (int)Math.ceil(data/2.0);
							System.out.println("-P�et�en�!-  P�lka dat se mus� poslat znovu (" + ubytek + ").");
							bw.write("-P�et�en�!-  P�lka dat se mus� poslat znovu (" + ubytek + ").");
							bw.newLine();
							ztratovost += ubytek;
							posliData(vterina, ubytek, dijkstra);

							System.out.println("\n--- vterina " + vterina + " ---");
							bw.write("--- vterina " + vterina + " ---");
							bw.newLine();
							bw.newLine();
							data = data - ubytek;

						}

					System.out.println("Z vrcholu " + prvni.getId() + " do vrcholu " + druhy.getId() + " bylo posl�no " + data + " dat.");
					bw.write("Z vrcholu " + prvni.getId() + " do vrcholu " + druhy.getId() + " bylo posl�no " + data + " dat.");
					bw.newLine();
					//poslat data dal

				}

				else{
					int pozastavenaData = data - propustnostHrany;
					poslano = propustnostHrany;
					rand = r.nextDouble();

					if (chybovostHrany != 1.0)
						if (rand < 0.5) {
							ubytek = (int)Math.ceil(propustnostHrany/2.0); 
							System.out.println("-P�et�en�!-  P�lka dat se mus� poslat znovu (" + ubytek + ").");
							bw.write("-P�et�en�!-  P�lka dat se mus� poslat znovu (" + ubytek + ").");
							bw.newLine();
							ztratovost += ubytek;
							posliData(vterina, ubytek, dijkstra);

							System.out.println("\n--- vterina " + vterina + " ---");
							bw.write("--- vterina " + vterina + " ---");
							bw.newLine();
							bw.newLine();
							poslano = propustnostHrany - ubytek;
						}
					System.out.println("Z vrcholu " + prvni.getId() + " do vrcholu " + druhy.getId() + " bylo posl�no " + poslano + " dat.");
					bw.write("Z vrcholu " + prvni.getId() + " do vrcholu " + druhy.getId() + " bylo posl�no " + poslano + " dat.");
					bw.newLine();
					//poslat data
					if (!prvni.stack.pridatPamet(pozastavenaData)) {
						System.out.println("Pam� vrcholu " + prvni.getId() + " p�etekla! Data se mus� poslat znovu (" +  pozastavenaData + ").");
						bw.write("Pam� vrcholu " + prvni.getId() + " p�etekla! Data se mus� poslat znovu (" +  pozastavenaData + ").");
						bw.newLine();
						ztratovost += pozastavenaData;
						posliData(vterina, pozastavenaData, dijkstra);
						break;
					}
					else
						System.out.println(pozastavenaData + " dat bylo ulo�eno do stacku vrcholu " + prvni.getId() + ".");
					bw.write(pozastavenaData + " dat bylo ulo�eno do stacku vrcholu " + prvni.getId() + ".");
					bw.newLine();

					while(pozastavenaData != 0) {
						vterina++;
						System.out.println("\n--- vterina " + vterina + " ---");
						bw.write("--- vterina " + vterina + " ---");
						bw.newLine();
						bw.newLine();

						if (propustnostHrany > pozastavenaData) {
							poslano = pozastavenaData;
							rand = r.nextDouble();

							if (((double)pozastavenaData / (double)propustnostHrany) > chybovostHrany) 
								if (rand < 0.5) {
									ubytek = (int)Math.ceil(pozastavenaData/2.0); 
									System.out.println("-P�et�en�!-  P�lka dat se mus� poslat znovu (" + ubytek + ").");
									bw.write("-P�et�en�!-  P�lka dat se mus� poslat znovu (" + ubytek + ").");
									bw.newLine();
									ztratovost += ubytek;
									posliData(vterina, ubytek, dijkstra);

									System.out.println("\n--- vterina " + vterina + " ---");
									bw.write("--- vterina " + vterina + " ---");
									bw.newLine();
									bw.newLine();
									poslano = pozastavenaData - ubytek;		
								}

							System.out.println("Z vrcholu " + prvni.getId() + " do vrcholu " + druhy.getId() + " bylo posl�no " + poslano + " dat.");
							bw.write("Z vrcholu " + prvni.getId() + " do vrcholu " + druhy.getId() + " bylo posl�no " + poslano + " dat.");
							bw.newLine();
							//poslat data
							prvni.stack.smazatData(pozastavenaData);
							System.out.println(pozastavenaData + " dat bylo smaz�no ze stacku vrcholu " + prvni.getId() + ".");
							bw.write(pozastavenaData + " dat bylo smaz�no ze stacku vrcholu " + prvni.getId() + ".");
							bw.newLine();
							break;
						}

						rand = r.nextDouble();

						poslano = propustnostHrany;
						if (chybovostHrany != 1.0)
							if (rand < 0.5) {
								ubytek = (int)Math.ceil(propustnostHrany/2.0); 
								System.out.println("-P�et�en�!-  P�lka dat se mus� poslat znovu (" + ubytek + ").");
								bw.write("-P�et�en�!-  P�lka dat se mus� poslat znovu (" + ubytek + ").");
								bw.newLine();
								ztratovost += ubytek;
								posliData(vterina, ubytek, dijkstra);

								System.out.println("\n--- vterina " + vterina + " ---");
								bw.write("--- vterina " + vterina + " ---");
								bw.newLine();
								bw.newLine();
								poslano = propustnostHrany - ubytek;	
							}

						System.out.println("Z vrcholu " + prvni.getId() + " do vrcholu " + druhy.getId() + " boly posl�no " + poslano + " dat.");
						bw.write("Z vrcholu " + prvni.getId() + " do vrcholu " + druhy.getId() + " boly posl�no " + poslano + " dat.");
						bw.newLine();
						//poslat data
						prvni.stack.smazatData(propustnostHrany);
						System.out.println(propustnostHrany + " dat bylo smaz�no ze stacku vrcholu " + prvni.getId() + ".");
						bw.write(propustnostHrany + " dat bylo smaz�no ze stacku vrcholu " + prvni.getId() + ".");
						bw.newLine();
						pozastavenaData -= propustnostHrany;	
					}

				}

			}
			bw.close();
		}

		catch (Exception e) {

			System.err.println("Nepoda�ilo se zapsat do souboru 'vstupTest.txt'.");
		}
	}

	public int vypisZtratovost() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("prubehSimulace.txt", true))) {
			System.out.println("\nZtr�tovost je " + ztratovost);
			bw.newLine();
			bw.write("Ztr�tovost je " + ztratovost);
			bw.newLine();
			bw.newLine();
			bw.close();
		}
		catch (Exception e) {

			System.err.println("Nepoda�ilo se zapsat do souboru 'vstupTest.txt'.");
		}
		return ztratovost;

	}


}
