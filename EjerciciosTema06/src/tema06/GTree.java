package tema06;
public class GTree<E> extends Tree<E> implements GTreeIF<E> {

	private ListIF<GTreeIF<E>> children; 
	
	/* Constructor por defecto: crea un árbol vacío */
	public GTree() {
		super();
		this.children = new List<GTreeIF<E>>();
	}

	public void setRoot(E e) {
		if (this.isEmpty() && e != null) {
			// Si el arbol estaba vacio su raíz
			// era null (tamaño 0).
			updateSizeUpwards(this, 1);
		}
		this.root = e;
	}

	public ListIF<GTreeIF<E>> getChildren() {
		return this.children;
	}

	public GTreeIF<E> getChild(int pos) {
		return this.children.get(pos);
	}
	
	
	/* Size */
	
	@Override
	public void setParent(GTree<E> t) {
		this.parent = t;
	}
	
	public GTree<E> getParent() {
		return (GTree<E>) this.parent;
	}

	public void addChild(int pos, GTreeIF<E> e) {
		this.children.insert(pos, e);
		e.setParent(this);
		updateSizeUpwards(this, e.size());
	}

	public void removeChild(int pos) {
		updateSizeUpwards(this, -this.getChild(pos).size());
		this.children.remove(pos);
	}
	
	private void updateSizeUpwards(GTree<E> node, int delta) {
        GTree<E> current = node;
        while (current != null) {
            current.size += delta;
            current = current.getParent();
        }
    }
	

	/* Reimplementación/Especialización de algunos métodos de Collection */
	public int size() {
		return size;
	}
	
	/* Devuelve el número de nodos del árbol */
	public int sizeED() {
		if ( isEmpty() ) { return 0; }
		int s = 1;
		IteratorIF<GTreeIF<E>> childIt = this.children.iterator();
		while ( childIt.hasNext() ) {
			s = s + childIt.getNext().size();
		}		
		return s;
	}
	
	
	/* Size */

	/* Vacía el árbol */
	public void clear() {
		super.clear();
		this.children.clear();
	}

	/* Métodos heredados de CollectionIF */
	
	/* Comprueba si el árbol contiene el elemento */
	public boolean contains(E e) {
		if ( isEmpty() ) { return false; }
		boolean found = getRoot().equals(e);
		IteratorIF<GTreeIF<E>> childIt = this.children.iterator();
		while ( !found && childIt.hasNext() ) {
			found = childIt.getNext().contains(e);
		}
		return found;
	}	

	/* Métodos heredados de TreeIF */
	
	/* Devuelve el número de hijos del árbol */
	public int getNumChildren() {
		return this.children.size();
	}

	/* Devuelve el fan-out del árbol */
	public int getFanOut() {
		if ( isEmpty() ) { return 0; }
		int fOut = getNumChildren();
		IteratorIF<GTreeIF<E>> childIt = this.children.iterator();
		while ( childIt.hasNext() ) {
			int aux = childIt.getNext().getFanOut();
			if ( aux > fOut ) { fOut = aux; }
		}
		return fOut;
	}

	/* Devuelve la altura del árbol */
	public int getHeight() {
		if ( isEmpty() ) { return 0; }
		int height = 0;
		IteratorIF<GTreeIF<E>> childIt = this.children.iterator();
		while ( childIt.hasNext() ) {
			int aux = childIt.getNext().getHeight();
			if ( aux > height ) { height = aux; }
		}
		return 1 + height;
	}

	/* Devuelve un iterador sobre el árbol según el recorrido elegido */
	public IteratorIF<E> iterator(Object mode) {
		QueueIF<E> queue = new Queue<E>();
		if ( mode instanceof GTree.IteratorModes ) {
			switch ((GTree.IteratorModes) mode) {
			case PREORDER:
				preorder(this,queue);
				break;
			case POSTORDER:
				postorder(this,queue);
				break;
			case BREADTH:
				breadthLR(this,queue);
				break;
			}
		}
		return queue.iterator();
	}
	
	/* Recorre el árbol en preorden */
	private void preorder(GTreeIF<E> t, QueueIF<E> q) {
		if ( !t.isEmpty() ) {
			q.enqueue(t.getRoot());
			IteratorIF<GTreeIF<E>> childIt = t.getChildren().iterator();
			while ( childIt.hasNext() ) {
				preorder(childIt.getNext(),q);
			}
		}
	}

	/* Recorre el árbol en postorden */
	private void postorder(GTreeIF<E> t, QueueIF<E> q) {
		if ( !t.isEmpty() ) {
			IteratorIF<GTreeIF<E>> childIt = t.getChildren().iterator();
			while ( childIt.hasNext() ) {
				postorder(childIt.getNext(),q);
			}
			q.enqueue(t.getRoot());
		}
	}

	/* Recorre el árbol en anchura de izquierda a derecha */
	private void breadthLR(GTreeIF<E> t, QueueIF<E> q) {
		if ( !t.isEmpty() ) {
			QueueIF<GTreeIF<E>> auxQ = new Queue<GTreeIF<E>>();
			auxQ.enqueue(t);
			while ( ! auxQ.isEmpty() ) {
				GTreeIF<E> cGT = auxQ.getFirst();
				q.enqueue(cGT.getRoot());
				IteratorIF<GTreeIF<E>> childIt = cGT.getChildren().iterator();
				while ( childIt.hasNext() ) {
					auxQ.enqueue(childIt.getNext());
				}
				auxQ.dequeue();
			}

		}
	}
	
	
	@SuppressWarnings("unchecked")
	public E getMax() {
	    IteratorIF<E> it = iterator(IteratorModes.PREORDER);
	    E max = null;
	    while (it.hasNext()) {
	        E elem = it.getNext();
	        Comparable<E> comparable = (Comparable<E>) elem;
	        if (max == null || comparable.compareTo(max) > 0) {
	            max = elem;
	        }
	    }
	    return max;
	}
	
	
	@SuppressWarnings("unchecked")
	public E getMax2b() {
	    E max = null;
	    if (!isEmpty()) {
	        QueueIF<GTreeIF<E>> auxQ = new Queue<GTreeIF<E>>();
	        auxQ.enqueue(this);
	        while (!auxQ.isEmpty()) {
	            GTreeIF<E> cGT = auxQ.getFirst();
	            auxQ.dequeue();
	            int height = cGT.getHeight();
	            System.out.println(height);
	            if (height == 1) { // nodo actual es una hoja
	                E leafValue = cGT.getRoot();
	                if (max == null || ((Comparable<E>) leafValue).compareTo(max) > 0) {
	                    max = leafValue;
	                }
	            }
	            IteratorIF<GTreeIF<E>> childIt = cGT.getChildren().iterator();
	            while (childIt.hasNext()) {
	                auxQ.enqueue(childIt.getNext());
	            }
	        }
	    }
	    return max;
	}


	
	public void view() {
	    if (isEmpty()) {
	        System.out.println("El árbol está vacío.");
	        return;
	    }

	    Queue<GTreeIF<E>> queue = new Queue<>();
	    queue.enqueue(this);

	    while (!queue.isEmpty()) {
	        int levelNodes = queue.size(); // Número de nodos en el nivel actual

	        // Imprimir nodos en el nivel actual
	        for (int i = 0; i < levelNodes; i++) {
	            GTreeIF<E> currentNode = queue.getFirst();
	            System.out.print(currentNode.getRoot() + " ");
	            queue.dequeue();

	            // Añadir nodos hijos al nivel siguiente a la cola
	            IteratorIF<GTreeIF<E>> childIt = currentNode.getChildren().iterator();
	            while (childIt.hasNext()) {
	                queue.enqueue(childIt.getNext());
	            }
	        }
	        System.out.println(); // Saltar a una nueva línea para el siguiente nivel
	    }
	}
	
	public void view2() {
	    if (isEmpty()) {
	        System.out.println("El árbol está vacío.");
	        return;
	    }

	    Queue<GTreeIF<E>> queue = new Queue<>();
	    queue.enqueue(this);

	    while (!queue.isEmpty()) {
	        int levelNodes = queue.size(); // Número de nodos en el nivel actual

	        // Imprimir nodos en el nivel actual con indentación
	        for (int i = 0; i < levelNodes; i++) {
	            GTreeIF<E> currentNode = queue.getFirst();
	            queue.dequeue();
	            int depth = getDepth(currentNode); // Obtener la profundidad del nodo
	            printIndentation(depth); // Imprimir indentación según la profundidad
	            System.out.println("- " + currentNode.getRoot());

	            // Añadir nodos hijos al nivel siguiente a la cola
	            IteratorIF<GTreeIF<E>> childIt = currentNode.getChildren().iterator();
	            while (childIt.hasNext()) {
	                queue.enqueue(childIt.getNext());
	            }
	        }
	    }
	}

	// Método para obtener la profundidad de un nodo en el árbol
	private int getDepth(GTreeIF<E> node) {
	    int depth = 0;
	    while (node.getRoot() != null) {
	        depth++;
	        node = node;
	    }
	    return depth;
	}

	// Método para imprimir la indentación según la profundidad del nodo
	private void printIndentation(int depth) {
	    for (int i = 0; i < depth; i++) {
	        System.out.print("    "); // Cuatro espacios por nivel de profundidad
	    }
	}

	@Override
	public GTreeIF<E> setParent() {
		// TODO Auto-generated method stub
		return null;
	}


}
