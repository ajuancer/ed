
public interface ListMSIF<E> extends SequenceMSIF<E>{
	
	/** Inserta un elemento en la lista
	 * 	
	 * @pre 0 <= pos <= size() + 1 <= maxSize()
	 * @param element Elemento a añadir
	 * @param pos Posición en la que añadir el elemento
	 */
	public void insert (E element, int pos);
	
}
