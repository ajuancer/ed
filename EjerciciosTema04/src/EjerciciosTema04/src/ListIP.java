package EjerciciosTema04.src;
import EjerciosTema04.src.Stack;

public class ListIP<E> extends Sequence<E> implements ListIPIF<E> {
	private static final Exception PointerOutOfBounds = null;

	private int size;

	private Stack<E> pointer;
	private Stack<E> remainder;
	
	// Constructor
	public ListIP() {
		this.pointer = new Stack<E>();
		this.remainder = new Stack<E>();
	}
	
	// Constructor por copia
	public ListIP(ListIP<E> l) {
		super(l);
		this.pointer = l.pointer;
		this.remainder = l.remainder;
		this.resetPointerPosition();
	}
	
	@Override
	public void setPointerPosition(int n) throws Exception {
		if (n > this.size) { throw PointerOutOfBounds; }
		
		if (getPointerPosition() == n) { return; }
		
		while (getPointerPosition() > n) {
			movePointerBackwards();
		}
		while (getPointerPosition() < n) {
			movePointerFowards();
		}
		
	}

	@Override
	public void movePointerFowards() throws Exception {
		if (!canPointerMoveFowards()) { throw PointerOutOfBounds; }
		pointer.push(remainder.getTop());
		remainder.pop();
	}

	@Override
	public void movePointerBackwards() throws Exception {
		if (!canPointerMoveBackwards()) { throw PointerOutOfBounds; }
		remainder.push(pointer.getTop());
		pointer.pop();	
	}

	@Override
	public void resetPointerPosition() {
		while (canPointerMoveBackwards()) {
			try {
				movePointerBackwards();
			} catch (Exception e) {
				// Esta situación nunca se da puesto que el
				// puntero se mueve sii es posible
				e.printStackTrace();
			}
		}
	}

	@Override
	public int getPointerPosition() {
		int s = this.pointer.size;
		if (s==0) { return 0; }
		return s - 1;
	}
	
	public boolean canPointerMoveFowards() {
		return getPointerPosition() < this.size - 1;
	}
	
	public boolean canPointerMoveBackwards() {
		return getPointerPosition() > 0;
	}
	

	@Override
	public E get() {
		return pointer.getTop();
	}

	@Override
	public void set(E e) {
		pointer.pop();
		pointer.push(e);
	}

	@Override
	public void insert(E e) {
		pointer.push(e);
		this.size++;
		
	}

	@Override
	public void remove() {
		pointer.pop();
		this.size--;
	}
	
}
