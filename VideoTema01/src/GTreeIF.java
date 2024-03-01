
public interface GTreeIF<E> extends TreeIF<E> {
	public enum IteratorModes {
		PREORDER, POSTORDER, BREADTH
	}
	
	public void setRoot(E e);
	
	public ListIF <GTreeIF<E>> getChildren ();
	
	public GTreeIF<E> getChild (int pos);
	
	public void addChild (int post, GTreeIF<E> e);
	
	public void removeChild (int pos);
}
