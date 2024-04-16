package EjerciciosTema04.src;
public class PrevList<E> extends PrevSequence<E>{
	

	

	


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
    
    public PrevList<E> sort() {
    	if (this.size < 2)
    		return this;
    	return this.sort(0, this.size-1);
    }
	
	private PrevList<E> sort(int low, int high) {
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
		PrevList<E> l;
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
	
	public PrevList<E> mezclar(PrevList<E> a, PrevList<E> b) {
		PrevList<E> r;
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
