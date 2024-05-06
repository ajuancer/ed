package tema05;

public class SequenceMS<E> extends Sequence<E> implements SequenceMSIF<E> {
	private int maxSize;
	protected static final Exception IndexOutOfBounds = null;


	@Override
	public int getMaxSize() {
		return maxSize;
	}

	@Override
	public boolean isFull() {
		return size() == getMaxSize();
	}
	
	public SequenceMS(int s) {
		super();
		this.maxSize = s;
	}
	
	public SequenceMS(SequenceMS<E> s) {
		super(s);
		this.maxSize = s.maxSize;
	}

}
