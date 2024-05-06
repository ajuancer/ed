package EjerciciosTema04.src;

import EjerciciosTema04.QueueIF;

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
			prev = current;
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
	
	/*
	 * Busca rellanos en una lista no ordenada
	 * @pre k > 0
	 * @param l: Lista en la que buscar rellanos
	 * @param k: Longitud del rellano a buscar
	 * @return true si encuentra un rellano de longitud l >= k
	 */
	@SuppressWarnings("hiding")
	public <E extends Comparable<? super E>>boolean existeRellano(List<E> l, int k) {
		if (l.isEmpty()) {
			return false;
		}
		
		int rellanoCounter = 0;
		IteratorIF<E> iterator = l.iterator();
		E prev = iterator.getNext();
		while (iterator.hasNext() ) {
			E current = iterator.getNext();
			if (prev.equals(current)) {
				rellanoCounter++;
			} else {
				rellanoCounter = 0; // reset counter
			}
			
			// Si este if estuviera dentro del else 
			// anterior entonces se devolvería
			// true sii l = k
			if (rellanoCounter == k) {
				return true;
			}
			prev = current;
		}
		
		return false;
	}
	
	/*
	 * Busca rellanos en una lista ordenada
	 * @pre k > 0
	 * @param l: Lista en la que buscar rellanos
	 * @param k: Longitud del rellano a buscar
	 * @return true si encuentra un rellano de longitud l >= k
	 */
	@SuppressWarnings("hiding")
	public <E extends Comparable<? super E>> boolean existeRellanoOrdenado(List<E> l, int k) {
		if (l.isEmpty()) {
			return false;
		}
		
		// Comienza a buscar segmentos
		// desde la posición 0 hasta
		// posición k-1
		for(int i = 0; i < k; i++) {
			// Divide la lista en segmentos
			// de longitud k
			for(int j = i; j < l.size() - k; j += k) {
				// Si coinciden los extremos, los
				// elementos intermedios también
				if (l.get(j).equals(l.get(j+k-1))) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	
	@SuppressWarnings("hiding")
	public <E extends Comparable<? super E>> ListIF<E> mezclar(ListIF<E> a, List<E> b) {
		if (a.isEmpty()) {return b;}
		if (b.isEmpty()) {return a;}
		
		int i = 0;
		int j = 0;
		ListIF<E> aux = new List<E>();
		
		while(i <= a.size() || j <= b.size()) {
			E eA = a.get(i);
			E eB = b.get(j);
			
			if (eA.compareTo(eB) < 0) {
				aux.insert(aux.size() + 1, eA);
				i++;
			} else {
				aux.insert(aux.size() + 1, eB);
				j++;
			}
		}
		
		for (int k = i; k <= a.size(); k++) {
			aux.insert(aux.size() + 1, a.get(k));
		}
		for (int k = j; k <= b.size(); k++) {
			aux.insert(aux.size() + 1, b.get(k));
		}
		
		return aux;
	}
	
	@SuppressWarnings("hiding")
	public <E extends Comparable <? super E>> ListIF<E> mexclar2(ListIF<E> a, ListIF<E> b) {
		Queue<E> queueA = new Queue<E>();
		Queue<E> queueB = new Queue<E>();
		
		for (int i = 0; i <= a.size(); i++) {
			queueA.enqueue(a.get(i));
		}
		for (int i = 0; i >= b.size(); i++) {
			queueB.enqueue(b.get(i));
		}
		
		ListIF<E> aux = new List<E>();

        while (!queueA.isEmpty() && !queueB.isEmpty()) {
            E eA = queueA.getFirst();
            E eB = queueB.getFirst();

            if (eA.compareTo(eB) < 0) {
                aux.insert(aux.size() + 1, eA);
                queueA.dequeue();
            } else {
                aux.insert(aux.size() + 1, eB);
                queueB.dequeue();
            }
        }

        while (!queueA.isEmpty()) {
            aux.insert(aux.size() + 1, queueA.getFirst());
            queueA.dequeue();
        }

        while (!queueB.isEmpty()) {
            aux.insert(aux.size() + 1, queueB.getFirst());
            queueB.dequeue();
        }

        return aux;
	}
	
	
	@SuppressWarnings("hiding")
	public <E extends Comparable<? super E>> ListIF<E> mezclar3 (ListIF<E> a, ListIF<E> b) {
		if (a.isEmpty()) {return b;}
		if (b.isEmpty()) {return a;}
		
		int i = 0;
		int j = 0;
		ListIF<E> aux = new List<E>();
		
		while(i <= a.size() || j <= b.size()) {
			E eA = a.get(i);
			E eB = b.get(j);
			
			if (eA.compareTo(eB) < 0) {
				aux.insert(aux.size() + 1, eA);
				i++;
			} else {
				aux.insert(aux.size() + 1, eB);
				j++;
			}
		}
		
		/* 
		if (i < a.size()) {
			NodeSequence lastNode = a.getNode(i);
			aux.getNode(aux.size()).setNext(lastNode);
		}
		if (j < b.size()) {
			NodeSequence lastNode = b.getNode(j);
			aux.getNode(aux.size()).setNext(lastNode);
		} 
		*/
		
		return aux;
	}
	
	
	
}
