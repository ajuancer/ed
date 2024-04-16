package EjerciciosTema04.src;

public class PrevListMS<E> extends PrevSequenceMS<E> implements ListMSIF<E> {
	
	private static final Exception IndexOutOfBounds = null;

	@Override
	public void insert(E element, int pos) {
		if (super.maxSize < pos) {
			throw IndexOutOfBounds;
		}
		if (pos==1) {
			newNode.setNext(this.firstNode);
			this.firstNode = newNode;
		} else {
			NodeSequence prevNode = getNode(pos-1);
			NodeSequence nextNode = previousNode.getNext();
			previousNode.setNext(newNode);
			newNode.setNext(nextNode);
		}
		size++;
	}

}
