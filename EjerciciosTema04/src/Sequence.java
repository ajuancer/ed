public abstract class Sequence<E> extends Collection<E> {
	
	protected class NodeSequence {
		private E value;
		
		private NodeSequence next;
		
		NodeSequence () { this.value = null; this.next = null; }
		
		NodeSequence (E e) { this.value = e; this.next = null; }
		
		public E getValue () { return this.value; }
		
		public void setValue (E e) { this.value = e; }
		
		public NodeSequence getNext() { return this.next; }
		
		public void setNext (NodeSequence n) { this.next = n; }
	}
	
	
	private class SequenceIterator implements IteratorIF<E>{

		private NodeSequence currentNode;
		
		SequenceIterator () { this.currentNode = firstNode; }
		
		public E getNext() {
			E elem = this.currentNode.getValue();
			this.currentNode = this.currentNode.getNext();
			return elem;
		}
		
		@Override
		public boolean hasNext() {
			return this.currentNode != null;
		}

		@Override
		public Object next() {
			
		}

		@Override
		public void remove() {
			this.currentNode = firstNode;
		}
		
		public IteratorIF<E> iterator () {
			return new SequenceIterator();
		}

	}
	
	
	protected NodeSequence firstNode;
	
	
	private NodeSequence getFirstNode () {
		return this.firstNode;
	}
	
	
	private NodeSequence getNode(int i) {
		NodeSequence node = firstNode;
		for (int aux = 1; aux < i; aux++) {
			node = node.getNext();
		}
		return node;
	}

	
	public Sequence () { super(); this.firstNode = null; }
	
	public Sequence (Sequence<E> s) {
		this ();
		
		if ( !s.isEmpty()) {
			this.size = s.size();
			NodeSequence nodeS = s.getFirstNode();
			NodeSequence pNode = new NodeSequence(nodeS.getValue());
			this.firstNode = pNode;
			while (nodeS.getNext() != null) {
				nodeS = nodeS.getNext();
				NodeSequence nNode = new NodeSequence(nodeS.getValue());
				pNode.setNext(nNode);
				pNode = nNode;
			}
		}
	}
	
	
	public void clear () { super.clear(); this.firstNode = null; }
	
	public boolean contains(E e) {
		NodeSequence node = this.firstNode;
		
		while(node != null) {
			E next = node.getValue();
			if (next.equals(e)) { return true; }
			node = node.getNext();
		}
		return false;
	}
}
