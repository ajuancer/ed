
public class Stack<E> extends Sequence<E> implements StackIF<E> {
	
	/* Constructor por defecto: crea una pila vacía */
	public Stack(){
		super();
	}
	
	/* Constructor por copia: delega en el constructor por copia *
	 * de la secuencia                                           */
    public Stack(Stack<E> s) {
    	super(s);
    }
    
    public Stack(List<E> l) {
    	super(l);
    }
    
    public Stack(Queue<E> q) {
    	super(q);
    }
	
	/* Devuelve el elemento en la cima de la pila */
	public E getTop() {
		return this.firstNode.getValue();
	}

	/* Añade un nuevo elemento a la cima de la pila */
	public void push(E elem) {
		NodeSequence newNode = new NodeSequence(elem);
		if(!isEmpty()){
			newNode.setNext(this.firstNode);
		}
		this.firstNode = newNode;
		this.size++;
	}

	/* Elimina el elemento situado en la cima de la pila */
	public void pop() {
		this.firstNode = this.firstNode.getNext();
		this.size--;
	}
	
	public void concatStackIF(StackIF<E> b) {
		if (!b.isEmpty()) {
			NodeSequence lastNode;
			if (!this.isEmpty()) {
				lastNode = this.getNode(this.size());
			} else {
				lastNode = new NodeSequence(b.getTop());
				this.firstNode = lastNode;
				b.pop();
			}
			while (!b.isEmpty()) {
				NodeSequence newNode = new NodeSequence(b.getTop());
				lastNode.setNext(newNode);
				lastNode = newNode;
				b.pop();
				this.size++;
			}
		}
	}
	
	public void concatStack(Stack<E> b) {
		if ( !b.isEmpty()) {
			if (!this.isEmpty()) {
				NodeSequence lastNode = this.getNode(this.size());
				lastNode.setNext(b.firstNode);
			} else {
				this.firstNode = b.firstNode;
			}
			this.size = this.size + b.size;
		}
	}
	
	public static int Fibonacci(int n) {
		if (n<2) {return n;}
		int nm1 = 1; int nm2 = 0;
		for (int aux = 2;aux<=n;aux++) {
			int sum = nm1 + nm2;
			nm2 = nm1;
			nm1 = sum;
		}
		return (nm1);
	}
	
	public static <E> Stack<E> invierte(Stack<E> s) {
		if (s.size < 2) { return s; }
		IteratorIF<E> iterator = s.iterator();
		Stack<E> res = new Stack<E>();
		while (iterator.hasNext()) {
			res.push(iterator.getNext());
		}
		return res;
	}
	
	public static <E> Stack<E> invierte2(Stack<E> s) {
		Stack<E> res = new Stack<E>();
		while(!s.isEmpty()) {
			res.push(s.getTop());
			s.pop();
		}
		return res;
	}
	
	public static <E> Stack<E> invierte3(Stack<E> s) {
		Stack<E> res = new Stack<E>();
		invierteAux(s, res);
	}
	
	
	private static <E> void invierteAux(Stack<E> s1, Stack<E> s2) {
		if (!s1.isEmpty()) {
			s2.push(s1.getTop());
			s1.pop();
			invierteAux(s1, s2);
		}
	}

}
