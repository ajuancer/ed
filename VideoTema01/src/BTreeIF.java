
public interface BTreeIF<E> extends TreeIF<E> {
	public enum IteratorModes {
		PREORDER, POSTORDER, BREADTH, INORDER, RLBREADTH
	}
	
	public void setRoot (E e);
	
	public BTreeIF<E> getLeftChild ();
	
	public void setLeftChild (BTreeIF <E> child);
	
	public void removeLeftChild ();
	
	public BTreeIF<E> getRightChild ();
	
	public void setRightChild (BTreeIF <E> child);
	
	public void removeRightChild ();
	
	
}
