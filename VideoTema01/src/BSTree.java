
public interface BSTree<E extends Comparable<E>> extends TreeIF<E> {
	public enum IteratorModes {
		DIRECTORDER, REVERSEORDER
	}
	
	public enum Order {
		ASCENDING, DESCENDING
	}
		
	public void add (E e);
	
	public void remove (E e);
	
	public Order getOrder ();
}
