package tema05;

public interface QueueMSIF<E> extends SequenceMSIF<E> {
	//...
	
	/** Añadir un elemento a la cola
	 * 
	 * @pre size() + 1 <= getMaxSize()
	 * @param elem Elemento a añadir
	 */
	public void enqueue (E elem);
	
	//....
}
