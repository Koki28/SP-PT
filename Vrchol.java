package sp;

import java.util.ArrayList;
import java.util.Stack;

public class Vrchol {

	public int id;
    public Stack<?> zasobnik; // dopln�me nejsp� jinak
	public ArrayList <Vrchol> sousedi;
	
	
	public Vrchol(int id) {
		
		this.id = id;
		this.sousedi = new ArrayList <Vrchol>();
      //zalo�en� na�eho z�sobn�ku
	}
	
	@Override
	public String toString() {
		
		String uzel = "ID uzlu :" + id;
		
		return uzel;
	}
	
	// mo�n� getId()? - zat�m nen� pro�
	
/*	public static void vypisSousedy() {
		
		System.out.println("Sousedi " + id + ". uzlu:");
		
		for(int i = 0; i < sousedi.size(); i++) {
			
			System.out.println(i);
		} 
	} */
}