
public interface IteratorIF<E> {
	boolean hasNext ();
	
	Object next( );

	/*
	 * @pre next ( ) has been called multiple times
	 */
	void remove( );
}
