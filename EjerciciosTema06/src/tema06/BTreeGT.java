package tema06;

public class BTreeGT<E> implements BTreeIF<E>{
	private GTree<E> sTree = new GTree<E>();

	@Override
	public E getRoot() {
		return sTree.getRoot();
	}

	@Override
	public boolean isLeaf() {
		return sTree.isLeaf();
	}

	@Override
	public int getNumChildren() {
		return sTree.getNumChildren();
	}

	@Override
	public int getFanOut() {
		return sTree.getFanOut();
	}

	@Override
	public int getHeight() {
		return sTree.getHeight();
	}

	@Override
	public TreeIF<E> getParent() {
		return sTree.getParent();
	}

	@Override
	public IteratorIF<E> iterator(Object mode) {
		return sTree.iterator(mode);
	}

	@Override
	public int size() {
		return sTree.size();
	}

	@Override
	public boolean isEmpty() {
		return sTree.isEmpty();
	}

	@Override
	public boolean contains(E e) {
		return sTree.contains(e);
	}

	@Override
	public void clear() {
		sTree.clear();
	}

	@SuppressWarnings("unchecked")
	@Override
	public BTreeIF<E> getLeftChild() {
		return (BTreeIF<E>) sTree.getChild(1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BTreeIF<E> getRightChild() {
		return (BTreeIF<E>) sTree.getChild(2);
	}

	@Override
	public void setRoot(E e) {
		sTree.getRoot();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setLeftChild(BTreeIF<E> child) {
		// sTree.removeChild(1); ?
		sTree.addChild(1, (GTreeIF<E>) child);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setRightChild(BTreeIF<E> child) {
		sTree.addChild(2, (GTreeIF<E>) child);
	}

	@Override
	public void removeLeftChild() {
		sTree.removeChild(1);
	}

	@Override
	public void removeRightChild() {
		sTree.removeChild(2);
	}
	
}
