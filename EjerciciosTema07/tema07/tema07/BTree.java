package tema07;

public class BTree<E> extends Tree<E> implements BTreeIF<E> {
	
	private BTreeIF<E> leftChild;
	private BTreeIF<E> rightChild;
		
	/* Constructor por defecto: crea un árbol binario vacío */
	public BTree(){
		super();
		this.leftChild = null;
		this.rightChild = null;
	}
		
	/* Devuelve el hijo izquierdo del árbol */
	public BTreeIF<E> getLeftChild() {
		return this.leftChild;
	}

	/* Devuelve el hijo derecho del árbol */
	public BTreeIF<E> getRightChild() {
		return this.rightChild;
	}

	/* Modifica la raíz */
	public void setRoot(E e) {
		this.root = e;
	}

	/* Modifica el hijo izquierdo */
	public void setLeftChild(BTreeIF<E> child) {
		this.leftChild = child;
	}

	/* Modifica el hijo derecho */
	public void setRightChild(BTreeIF<E> child) {
		this.rightChild = child;
	}

	/* Elimina el hijo izquierdo */
	public void removeLeftChild() {
		this.leftChild = null;
	}

	/* Elimina el hijo derecho */
	public void removeRightChild() {
		this.rightChild = null;
	}

	/* Reimplementación/Especialización de algunos métodos de Collection */
	
	/* Devuelve el número de nodos del árbol */
	public int size() {
		if ( isEmpty() ) { return 0; }
		int s = 1;
		if ( this.leftChild != null ) { s = s + this.leftChild.size(); }
		if ( this.rightChild != null ) { s = s + this.rightChild.size(); }
		return s;
	}

	/* Vacía el árbol binario */
	public void clear() {
		super.clear();
		this.leftChild = null;
		this.rightChild = null;
	}
	
	/* Métodos heredados de CollectionIF */
	
	/* Comprueba si el árbol binario contiene el elemento */
	public boolean contains(E e) {
		return ( !isEmpty() && ( this.root.equals(e) ||   
								 ( this.leftChild != null && this.leftChild.contains(e) ) ||
								 ( this.rightChild != null && this.rightChild.contains(e) ) ) );
	}

	/* Métodos heredados de TreeIF */
	
	/* Devuelve el número de hijos del árbol */
	public int getNumChildren() {
		int nC = 0;
		if ( this.leftChild != null ) { nC++; }
		if ( this.rightChild != null ) { nC++; }
		return nC;
	}

	/* Devuelve el fan-out del árbol */
	public int getFanOut() {
		if ( getNumChildren() == 2 ) { return 2; }
		if ( this.leftChild != null ) { return Math.max(1,this.leftChild.getFanOut()); }
		if ( this.rightChild != null ) { return Math.max(1,this.rightChild.getFanOut()); }
		return 0;
	}

	/* Devuelve la altura del árbol */
	public int getHeight() {
		if ( isEmpty() ) { return 0; }
		int hLC = 0;
		if ( this.leftChild != null ) { hLC = this.leftChild.getHeight(); }
		int hRC = 0;
		if ( this.rightChild != null ) { hRC = this.rightChild.getHeight(); }
		return 1 + ((hLC > hRC)?hLC:hRC);
	}

	/* Devuelve un iterador sobre el árbol según el recorrido elegido */
	public IteratorIF<E> iterator(Object mode) {
		QueueIF<E> queue = new Queue<E>();
		if ( mode instanceof BTreeIF.IteratorModes ) {
			switch ((BTreeIF.IteratorModes) mode) {
				case PREORDER:
					preorder(this,queue);
					break;
				case INORDER:
					inorder(this,queue);
					break;
				case POSTORDER:
					postorder(this,queue);
					break;
				case BREADTH:
					breadthLR(this,queue);
					break;
				case RLBREADTH:
					breadthRL(this,queue);
					break;
			}
		}
		return queue.iterator();
	}
	
	/* Recorre el árbol en preorden */
	private void preorder(BTreeIF<E> t, QueueIF<E> q) {
		if ( !t.isEmpty() ) {
			q.enqueue(t.getRoot());
			if ( t.getLeftChild() != null ) { preorder(t.getLeftChild(),q); }
			if ( t.getRightChild() != null ) { preorder(t.getRightChild(),q); }
		}
	}
	
	/* Recorre el árbol en inorden */
	private void inorder(BTreeIF<E> t, QueueIF<E> q) {
		if ( !t.isEmpty() ) {
			if ( t.getLeftChild() != null ) { inorder(t.getLeftChild(),q); }
			q.enqueue(t.getRoot());
			if ( t.getRightChild() != null ) { inorder(t.getRightChild(),q); }
		}
	}

	/* Recorre el árbol en postorden */
	private void postorder(BTreeIF<E> t, QueueIF<E> q) {
		if ( !t.isEmpty() ) {
			if ( t.getLeftChild() != null ) { postorder(t.getLeftChild(),q); }
			if ( t.getRightChild() != null ) { postorder(t.getRightChild(),q); }
			q.enqueue(t.getRoot());
		}
	}

	/* Recorre el árbol en anchura de izquierda a derecha */
	private void breadthLR(BTreeIF<E> t, QueueIF<E> q) {
		if ( !t.isEmpty() ) {
			QueueIF<BTreeIF<E>> auxQ = new Queue<BTreeIF<E>>();
			auxQ.enqueue(t);
			while ( ! auxQ.isEmpty() ) {
				BTreeIF<E> cBT = auxQ.getFirst();
				q.enqueue(cBT.getRoot());
				if ( cBT.getLeftChild() != null ) { auxQ.enqueue(cBT.getLeftChild()); }
				if ( cBT.getRightChild() != null ) { auxQ.enqueue(cBT.getRightChild()); }
				auxQ.dequeue();
			}
		}
	}

	/* Recorre el árbol en anchura de derecha a izquierda */
	private void breadthRL(BTreeIF<E> t, QueueIF<E> q) {
		if ( !t.isEmpty() ) {
			QueueIF<BTreeIF<E>> auxQ = new Queue<BTreeIF<E>>();
			auxQ.enqueue(t);
			while ( ! auxQ.isEmpty() ) {
				BTreeIF<E> cBT = auxQ.getFirst();
				q.enqueue(cBT.getRoot());
				if ( cBT.getRightChild() != null ) { auxQ.enqueue(cBT.getRightChild()); }
				if ( cBT.getLeftChild() != null ) { auxQ.enqueue(cBT.getLeftChild()); }
				auxQ.dequeue();
			}
		}
	}
	
	public BTree(List<BTreeIF<E>> list, BTreeIF.IteratorModes mode) {
		super();
		switch (mode) {
			case BREADTH:
				break;
			case INORDER:
				break;
			case POSTORDER:
				BTreeIF<E> t = BTreeFromPostorderList(list, 1, list.size);
				this.leftChild = t.getLeftChild();
				this.rightChild = t.getRightChild();
				this.root = t.getRoot();
				break;
			case PREORDER:
				BTreeIF<E> t1 = BTreeFromPreorderList(list, 1, list.size);
				this.leftChild = t1.getLeftChild();
				this.rightChild = t1.getRightChild();
				this.root = t1.getRoot();
				break;
			case RLBREADTH:
				break;
			default:
				break;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private BTreeIF<E> BTreeFromPreorderList(List<BTreeIF<E>> list, Integer start, Integer end) {
		if (start > end) {
			return null;
		}
		
		// En preorden se recorre primero el nodo raíz, después el subarbol izq 
		// y finalmente el subárbol dcho
		BTreeIF<E> root = list.get(1);
		
		// El subárbol izq serán los valores menores a la raíz
		int i;
		for (i = start +1; i <= end; i++) {
			if (((Comparable<E>) list.get(i).getRoot()).compareTo(root.getRoot()) > 0) {
				break;
			}
		}
		
		root.setLeftChild(BTreeFromPreorderList(list, start + 1, i - 1));
		root.setRightChild(BTreeFromPreorderList(list, i, end));
		
		return root;
	}
	
	
	@SuppressWarnings("unchecked")
	private BTreeIF<E> BTreeFromPostorderList(List<BTreeIF<E>> list, Integer start, Integer end) {
		if (start > end) {
			return null;
		}
		
		// En postorder se recorre primero el subarbol izq, después el dcho y
		// finalmente el nodo raíz
		BTreeIF<E> root = list.get(list.size);
		
		// El subárbol izq serán los valores menores a la raíz
		int i;
		for (i = start; i <= end - 1; i++) {
			if (((Comparable<E>) list.get(i).getRoot()).compareTo(root.getRoot()) > 0) {
				break;
			}
		}
		
		root.setLeftChild(BTreeFromPostorderList(list, start, i - 1));
		root.setRightChild(BTreeFromPostorderList(list, i, end - 1));
		
		return root;
	}

}
