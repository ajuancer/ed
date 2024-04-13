
public class SequenceMS<E> extends Sequence<E> implements SequenceMSIF<E> {
	
	int maxSize = 10;

	@Override
	public int getMaxSize() {
		return maxSize;
	}

	/** Comprueba si queda espacio en la secuencia
	 * 
	 * @return bool verdadero si no caben más elementos
	 */
	@Override
	public boolean isFull() {
		return size() == getMaxSize();
	}

	@Override
	public IteratorIF<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
