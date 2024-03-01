
public interface ListIF<E> extends SequenceIF<E>{
	public E get (int post);
	
	public void set (int pos, E e);
	
	public void insert (E element, int pos);
	
	public void remove (int pos);
}
