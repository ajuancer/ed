public abstract class Collection<E> {
	protected int size;
	
	public Collection () { size = 0; }
	
	public int size () { return size; }
	
	public boolean isEmpty () { return size == 0; }
	
	public void clear () { size = 0; }
	
	abstract public boolean contains(E e);
}
