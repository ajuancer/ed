package tema05;

public interface StackMSIF<E> extends SequenceMSIF<E> {
	
	//...
	
	/** Push element into the stack
	 * 
	 * @pre size() + 1 <= getMaxSize()
	 * @param elem Element to add
	 */
	public void push (E elem);
	
	//...
	
}
