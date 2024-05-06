package EjerciciosTema04.src;

public abstract class ListMS<E> extends SequenceMS<E> implements ListMSIF<E> {
	
	public ListMS(int s) {
		super(s);
	}
	
	public ListMS(ListMS<E> l) {
		super(l);
	}

	/* Devuelve el elemento pos-ésimo de la lista */
	public E get(int pos) throws Exception {
		if (pos > this.getMaxSize()) {throw IndexOutOfBounds;}
		NodeSequence node = getNode(pos);
		return node.getValue();
	}

	/* Modifica el elemento pos-ésimo de la lista */
	public void set(int pos, E e) throws Exception {
		if (pos > this.getMaxSize()) {throw IndexOutOfBounds;}
		NodeSequence node = getNode(pos);
		node.setValue(e);
	}

	/* Inserta un nuevo elemento en la lista en la posición *
	 * indicada                                             */
	public void insert(int pos, E elem) throws Exception {
		if (pos > this.getMaxSize()) {throw IndexOutOfBounds;}
		NodeSequence newNode = new NodeSequence(elem);
		if(pos==1){
			newNode.setNext(this.firstNode);
			this.firstNode = newNode;
		}else{
			NodeSequence previousNode = getNode(pos-1);
			NodeSequence nextNode = previousNode.getNext();
			previousNode.setNext(newNode);
			newNode.setNext(nextNode);
		}
		this.size++;
	}

	/* Elimina el elemento pos-ésimo de la lista */
	public void remove(int pos) throws Exception {
		if (pos > this.getMaxSize()) {
			throw IndexOutOfBounds;
		} else if (pos==1){
			this.firstNode = this.firstNode.getNext();
		} else {
			NodeSequence previousNode = getNode(pos-1);
			NodeSequence nextNode = previousNode.getNext().getNext();
			previousNode.setNext(nextNode);
		}
		this.size--;
	}

}
