
public class SequenceMI<E> implements SequenceMSIF<E>  {

	@Override
	public IteratorIF<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getMaxSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	/** Comprueba si queda espacio en la secuencia
	 * 
	 * @return bool verdadero si no caben más elementos
	 */
	@Override
	public boolean isFull() {
		return size() == getMaxSize();
	}

}
