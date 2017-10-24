package sp;

import java.util.ArrayList;
import java.util.Stack;

public class Vrchol {

	int id;
	Stack<?> zasobnik;
	int kapacita;
	ArrayList<Vrchol> sousedi;
	boolean prazdny; // napul k nicemu, je potreba videt kolik je mista a kolik se vejde
	
	public Vrchol(int id) { //doplni se pozdeji
		
		this.id = id;
		this.sousedi = new ArrayList <Vrchol>();
	}
	
	@Override
	public String toString() {
		
		String uzel = "ID uzlu :" + id;
		
		return uzel;
	}
	
	public void vypisSousedy() {
		
		System.out.println("Sousedi " + id + ". uzlu:");
		
		for(int i = 0; i < sousedi.size(); i++) {
			
			System.out.println(i);
		}
	}
}