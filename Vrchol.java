package sp;

import java.util.ArrayList;
import java.util.Stack;

public class Vrchol {

	public int id;
    public StackPameti stack;
	public ArrayList <Vrchol> sousedi;
	
	
	public Vrchol(int id) {
		
		this.id = id;
		this.sousedi = new ArrayList <Vrchol>();
		this.stack = new StackPameti();

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