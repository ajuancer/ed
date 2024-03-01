
public interface QueueIF<E> extends SequenceIF<E> {
	public E getFirst ();
	
	public void enqueue (E elem);
	
	public void dequeue ();
}
