
public interface StackIF<E> extends SequenceIF<E> {
	public E getTop ();
	
	public void push (E elem);
	
	public void pop ();
	
	
}
