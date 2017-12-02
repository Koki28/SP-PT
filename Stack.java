package sp;

/**
 * Instance of class {@code Stack} representing memory
 * stack of every single node of computer web.
 *
 * @author  Pavel Prùcha and Tomáš Slíva
 */
public class Stack {

	/** Sum memory of node. */
	private int memorySum;
	
	/** Maximum memory stack of node */
	private final int MAX_MEMORY = 800;
	
	/**
	 * This method is adding memory to the memory
	 * stack, when all memory isn´t sent in one step.
	 * 
	 * @param memory  Memory in addition.
	 * 
	 * @return  True or false.
	 */
	public boolean addMemory(int memory) {
		
		if((memory + memorySum) > MAX_MEMORY) {
			
			memorySum += memory;
			return true;
		}
		
		return false;
	}

	/**
	 * This method is deleting selected data from memory stack.
	 * 
	 * @param data  Selected data.
	 */
	public void deleteData(int data) {
		
		memorySum -= data;
	}
	
	/**
	 * This method gives info about memory.
	 * 
	 * @return  Info about memory.
	 */
	public int dataInfo() {
		
		return MAX_MEMORY - memorySum;
	}
}