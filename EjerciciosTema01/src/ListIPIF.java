
public interface ListIPIF<E> extends SequenceIF<E> {
	/** Fija la posición del puntero en la posicón n
	 * 
	 * @pre 0 <= n <= size()
	 * 															**/
	public void setPointerPosition(int n);
	
	/** Avanza la posición del puntero una unidad
	 * 
	 * @pre 1 <= getPointerPosition() + 1 <= size() + 1
	 * 															**/
	public void movePointerFowards();
	
	/** Retrocede la posición del puntero una unidad
	 * 
	 * @pre 0 <= getPointerPosition() - 1 <= size() + 1
	 * 															**/
	public void movePointerBackwards();
	
	/** Fija la posición del puntero a la posición 0
	 * 
	 * @post getPointerPosition() = 0
	 * 															**/
	public void resetPointerPosition();
	
	/** Devuelve la posición actual del puntero
	 * 
	 * @return La posición del puntero
	 * 															**/
	public int getPointerPosition();
	
	/** Devuelve el elemento en la posición del puntero
	 * 
	 * @pre Existe un elemento en la posición del puntero
	 * 															**/
	public E get ();
	
	/** Fija el elemento en la posición del puntero
	 * 
	 * @pre Existe un elemento en la posición del puntero
	 * 															**/
	public void set (E e);
	
	/** Inserta un elemento en la posición del puntero
	 * @pre No existe un elemento en la posición del puntero
	 * @post Existe un elemento en la posición del puntero
	 * 															**/
	public void insert (E element);
	
	/** Remove the element in the pointed position 
	 * 
	 * @pre Existe un elemento en la posición del puntero
	 * @post No existe un elemento en la posición del puntero
	 * 															**/
	public void remove ();
}
