
public interface TreeIF<E> extends CollectionIF<E> {
	public E getRoot ();
	
	public boolean isLeaf ();
	
	public int getNumChildren ();
	
	public int getFanOut ();
	
	public int getHeight ();
	
	public IteratorIF<E> iterator (Object mode);
}
