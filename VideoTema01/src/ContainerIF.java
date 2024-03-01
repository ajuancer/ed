
public interface ContainerIF<E> extends CollectionIF<E> {
	public void add (E e);
	
	public void remove(E e);
	
	public IteratorIF<E> iterator ();

}
