
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
	
	public List<E> inverte(List<E> l) {
		if (l.size < 2) {
			return l;
		}
		List<E> invertedList = new List();
		for (int i=l.size-1; i>=0; i--) {
			invertedList.insert(l.size-i, l.get(i));
		}
		return invertedList;
	}
	
	public List<E> invierte(List<E> l) {
		if (l.size < 2) {
			return l;
		}
		List<E> invertedList = new NodeSequence();
		NodeSequence iterator = new SequenceIterator(l);
		int index = l.size -1;
		while (iterator.hasNext()) {
			invertedList.insert(index--, iterator.getNext());
		}
		return invertedList;
	}
	
	public boolean esOrdenada(List<E> l) {
		if (l.size < 3) {
			return true;
		}
		NodeSequence iterator = new SequenceIterator(l);
		NodeSequence prev = iterator.getValue();
		boolean increment = false;
		boolean decrement = false;
		while (iterator.hasNext()) {
			NodeSquence current = iterator.getNext();
			if (prev < current) {
				increment = true;
			} else if (prev > current) {
				decrement = true;
			}
			if (increment && decrement) {
				return false;
			}
		}
		return true;
	}
	
    private void swap(int i, int j) {
        E temp = this.get(i);
        this.set(i,  this.get(j));
        this.set(j, temp);
    }
    
    private int partition(int low, int high) {
        E pivot = this.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (this.get(j) < pivot) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }
    
    public List<E> sort() {
    	if (this.size < 2)
    		return this;
    	return this.sort(0, this.size-1);
    }
	
	private List<E> sort(int low, int high) {
		if (low < high) {
            int pivotIndex = this.partition(low, high);
            this.sort(low, pivotIndex - 1);
            this.sort(pivotIndex + 1, high);
        }
	}
	

	public boolean existeRellano() {
		if (this.size < 2) {
			return false;
		}
		
		boolean isSorted = esOrdenada(this);
		List<E> l;
		if (!isSorted) {
			l = this.sort();
		}
		
		NodeSequence iterator = new SequenceIterator(this);
		NodeSequence prev = iterator.getValue();
		while (iterator.hasNext()) {
			NodeSquence current = iterator.getNext();
			if (prev == current) {
				return true;
			}
		}
		return false;
	}
	
	public List<E> mezclar(List<E> a, List<E> b) {
		List<E> r;
		NodeSequence a_itr = new SequenceIterator(a);
		NodeSequence b_itr = new SequenceIterator(b);
		int a_i = 0;
		int b_i = 0;
		
		while(a_itr.hasNext() && b_itr.hasNext()) {
			E a_next = a_itr.getNext();
			E b_next = b_itr.getNext();
			if (a_next < b_next) {
                r.insert(a_i, a_next);
                a_i++;
            } else {
                r.insert(b_i, b_next);
                b_i++;
            }
		}
		
        while (a_i < a.size()) {
            r.insert(++a_i, a.get(a_i));
        }
        
        while (b_i < b.size()) {
            r.insert(++b_i, b.get(b_i));
        }
        return r;
	}
	
}
