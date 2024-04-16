package EjerciciosTema04.src;

public class List<E> extends Sequence<E> implements ListIF<E> {

	/* Constructor por defecto: crea una lista vacía */
	public List(){
		super();
	}
	
	/* Constructor por copia: delega en el constructor por copia *
	 * de la secuencia                                           */
    public List(List<E> s) {
    	super(s);
    }
	
    /* Devuelve el elemento pos-ésimo de la lista */
	public E get(int pos) {
		NodeSequence node = getNode(pos);
		return node.getValue();
	}

	/* Modifica el elemento pos-ésimo de la lista */
	public void set(int pos, E e) {
		NodeSequence node = getNode(pos);
		node.setValue(e);
	}

	/* Inserta un nuevo elemento en la lista en la posición *
	 * indicada                                             */
	public void insert(int pos, E elem) {
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
	public void remove(int pos) {
		if(pos==1){
			this.firstNode = this.firstNode.getNext();
		}else{
			NodeSequence previousNode = getNode(pos-1);
			NodeSequence nextNode = previousNode.getNext().getNext();
			previousNode.setNext(nextNode);
		}
		this.size--;
	}
	
	
	
	public <E extends Comparable<? super E>> boolean esOrdenada(List<E> l) {
		if (l.size < 3) {
			return true;
		}
		
		IteratorIF<E> iterator = l.iterator();
		E prev = iterator.getNext();
		boolean increment = false;
		boolean decrement = false;
		
		while (iterator.hasNext()) {
			E current = iterator.getNext();
			if (prev.compareTo(current) < 0) {
				increment = true;
			} else if (prev.compareTo(current) > 0) {
				decrement = true;
			}
			if (increment && decrement) {
				return false;
			}
		}
		return true;
	}
	
	
	public List<E> invierte1(List<E> l) {
		if (l.size < 2) {
			return l;
		}
		
		List<E> invertedList = new List<E>();
		for (int i=l.size-1; i>=0; i--) {
			invertedList.insert(l.size-i, l.get(i));
		}
		return invertedList;
	}
	
	public List<E> invierte2(List<E> l) {
		if (l.size < 2) {
			return l;
		}
		
		List<E> invertedList = new List<E>();
		IteratorIF<E> iterator = l.iterator();
		int index = l.size -1;
		while (iterator.hasNext()) {
			invertedList.insert(index--, iterator.getNext());
		}
		return invertedList;
	}
}
