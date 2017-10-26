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
		
		String uzel = "ID uzlu: " + id;
		
		return uzel;
	}
	
	public int getId() {
		
		return id;
	}
}