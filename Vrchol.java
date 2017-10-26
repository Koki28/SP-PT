package sp;

import java.util.ArrayList;
import java.util.Stack;

public class Vrchol {

	public int id;
    public Stack<?> zasobnik; // doplníme nejspíš jinak
	public ArrayList <Vrchol> sousedi;
	
	
	public Vrchol(int id) {
		
		this.id = id;
		this.sousedi = new ArrayList <Vrchol>();
      //založení našeho zásobníku
	}
	
	@Override
	public String toString() {
		
		String uzel = "ID uzlu: " + id;
		
		return uzel;
	}
	
	public int getId() {
		
		return id;
	}
}