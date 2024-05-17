package tema05;

public class QueueMS<E> extends SequenceMS<E> implements QueueMSIF<E> {
	private NodeSequence lastNode;

	public QueueMS(SequenceMS<E> s) {
		super(s);
		if ( this.isEmpty() ) {
			this.lastNode = null;
		} else {
			this.lastNode = this.getNode(this.size);
		}
	}
	
	public QueueMS(int maxSize){
		super(maxSize);
		this.lastNode = null; 
	}

	public E getFirst() {
		return this.firstNode.getValue();
	}

	public void enqueue(E elem) {
		if (size() < getMaxSize()) {
			NodeSequence newNode = new NodeSequence(elem);
			if(isEmpty()){
				this.firstNode = newNode;
			} else{
				this.lastNode.setNext(newNode);
			}
			this.lastNode = newNode;
			this.size++;
		}
	}

	public void dequeue() {
		this.firstNode = this.firstNode.getNext();
		this.size--;
		if(this.size == 0){
			this.lastNode = null;
		}
	}
	
	public void clear() {
		super.clear();   
		this.lastNode = null;
	}

}
