package sp;

import java.util.ArrayList;
import java.util.Stack;

public class Vrchol {

	public static int id;
	public Stack<?> zasobnik;
	public int kapacita;
	public static ArrayList <Vrchol> sousedi;
	public boolean prazdny; // napul k nicemu, je potreba videt kolik je mista a kolik se vejde
	
	public Vrchol(int id) { //doplni se pozdeji
		
		Vrchol.id = id;
		Vrchol.sousedi = new ArrayList <Vrchol>();
	}
	
	@Override
	public String toString() {
		
		String uzel = "ID uzlu :" + id;
		
		return uzel;
	}
	
	public static void vypisSousedy() {
		
		System.out.println("Sousedi " + id + ". uzlu:");
		
		for(int i = 0; i < sousedi.size(); i++) {
			
			System.out.println(i);
		} 
	}
}