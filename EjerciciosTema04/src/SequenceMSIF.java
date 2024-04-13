
public interface SequenceMSIF<E> extends SequenceIF<E> {
	/** Devuelve el número máximo de elementos que puede almacenar las secuencias
	 * 
	 * @return Número máximo de elementos
	 */
	public int getMaxSize ();
	
	/** Comprueba si queda espacio en la secuencia
	 * 
	 * @return bool verdadero si no caben más elementos
	 */
	public boolean isFull();

}
