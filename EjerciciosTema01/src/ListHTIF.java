
public interface ListHTIF<E> extends SequenceIF<E>{
	/** Devuelve la cabeza de la lista.
	 * 
	 * @pre !isNull()
	 * 															**/
	public E getHead ();
	
	/** Devuelve la cola de la lista, lo que es equivalente
	 * 	a eliminar el elemento cabeza.
	 * 
	 * 															**/
	public ListHTIF<E> getTail ();
	
	/** Fija la cabeza de la lista
	 * 
	 * @pre !isNull()
	 * @return La nueva lista cuya cabeza se ha fijado
	 * 															**/
	public ListHTIF<E> setHead (E e);
	
	/** Fija la cola de la lista
	 * 
	 * @pre !isNull()
	 * @return La nueva lista cuya cola se ha fijado
	 * 															**/
	public ListHTIF<E> setTail (ListHTIF<E> e);
	
	/** Inserta un elemento en la posición del puntero
	 * 
	 * @return La nueva lista a cuya cola se ha añadido 
	 * 		   el elemento
	 * 															**/
	public ListHTIF<E> insertTail (E element);
	

}
