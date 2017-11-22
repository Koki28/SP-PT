package sp;

/**
 * Instance of class {@code Stack} representing memory
 * stack of every single node of computer web.
 *
 * @author  Pavel Prùcha and Tomáš Slíva
 */
public class Stack {

	/** Sum memory of node. */
	private int memory;
	
	/** Maximum memory stack of node */
	private final int MAX_MEMORY = 800;
	
	/**
	 * Constructor creating memory stack of node.
	 */
	public Stack() {

	}
	
	/**
	 * This method is adding memory to the memory
	 * stack, when all memory isn´t sent in one step.
	 * 
	 * @param addMemory  Memory in addition.
	 * 
	 * @return  True or false.
	 */
	public boolean addMemory(int addMemory) {
		
		if(checkMemory(addMemory)) {
			
			memory += addMemory;
			return true;

		} else {
			
			return false;
		}
	}
	
	/**
	 * This method is checking if the memory stack is full or not.
	 * 
	 * @param addMemory  Memory in addition.
	 * 
	 * @return  True or false.
	 */
	public boolean checkMemory(int addMemory) {
		
		 if((addMemory + memory) > MAX_MEMORY) {
			 
			 return false;
		 }
		
		return true;
	}

	/**
	 * This method is deleting selected data from memory stack.
	 * 
	 * @param data  Selected data.
	 */
	public void deleteData(int data) {
		
		memory -= data;
	}
}