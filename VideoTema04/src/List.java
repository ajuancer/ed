
public class List<E> extends Sequence<E>{
	public List () { super (); }
	
	public List (List<E> s) { super(s); }
	
	public E get (int pos) {
		NodeSequence node = getNode(pos);
		return node.getValue();
	}
	
	public void set (int pos, E e) {
		NodeSequence node = getNode(pos);
		node.setValue(e);
	}
	
	public void insert (int pos, E e) {
		NodeSequence newNode = new NodeSequence(e);
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
	
	public void remove (int pos) {
		if (pos==1) {
			this.firstNode = this.firstNode.getNext();
		} else {
			NodeSequence previousNode = this.getNode(pos-1);
			NodeSequence nextNode = previousNode.getNext().getNext();
			previousNode.setNext(nextNode);
		}
		size--;
	}
}
