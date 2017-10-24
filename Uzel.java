package sp;

import java.util.ArrayList;
import java.util.Stack;

public class Uzel {

	int id;
	Stack<?> zasobnik;
	int kapacita;
	ArrayList<Uzel> uzly;
	boolean prazdny; // napul k nicemu, je potreba videt kolik je mista a kolik se vejde
	
	public Uzel(int id) { //doplni se pozdeji
		
		this.id = id;
	}
	
	public String toString() {
		
		String uzel = "ID uzlu :" + id;
		
		return uzel;
	}
}
