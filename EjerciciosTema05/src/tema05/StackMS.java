package tema05;

import tema05.Sequence.NodeSequence;

public class StackMS<E> extends SequenceMS<E> implements StackMSIF<E>{

	public StackMS(int s) {
		super(s);
	}
	
	public StackMS(SequenceMS<E> e) {
		super(e);
	}

	/* Añade un nuevo elemento a la cima de la pila */
	public void push(E elem) {
		if (size() < getMaxSize()) {
			NodeSequence newNode = new NodeSequence(elem);

			if(!isEmpty()){
				newNode.setNext(this.firstNode);
			}
			this.firstNode = newNode;
			this.size++;
		}
	}
	
	/* Elimina el elemento situado en la cima de la pila */
	public void pop() {
		this.firstNode = this.firstNode.getNext();
		this.size--;
	}
	
	/* Devuelve el elemento en la cima de la pila */
	public E getTop() {
		return this.firstNode.getValue();
	}

}
