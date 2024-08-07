package tema06;

public class GTreeBT<E> implements GTreeIF<E>{
	BTreeIF<E> sTree = new BTree<E>();

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
		return (GTreeIF<E>) sTree.getParent();
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

	@Override
	public void setRoot(E e) {
		sTree.setRoot(e);
	}

	@SuppressWarnings({ "unchecked", "null" })
	@Override
	public ListIF<GTreeIF<E>> getChildren() {
		BTreeIF<E> children = sTree.getLeftChild();
		ListIF<GTreeIF<E>> res = null;
		do {
			res.insert(res.size(), (GTreeIF<E>) children);
		} while (children.getRightChild() != null);
		return res;
	}

	@Override
	public GTreeIF<E> getChild(int pos) {
		BTreeIF<E> children = sTree.getLeftChild();
		GTreeIF<E> res = null;
		int index = 1;
		while (children.getRightChild() != null && index > pos) {
			children = children.getRightChild();
			index++;
		}
		return res;
	}


	@Override
	public void addChild(int pos, GTreeIF<E> e) {
		this.getChild(pos).addChild(2, e);
	}

	@Override
	public void removeChild(int pos) {
		// Nodo que precede al que se quiere eliminar
		BTreeIF<E> prevDel = sTree.getLeftChild();
		int index = 1;
		while (prevDel.getRightChild() != null && index > pos) {
			prevDel = prevDel.getRightChild();
		}
		// Nodo que sigue al que se quiere eliminar
		BTreeIF<E> postDel = prevDel.getRightChild();
		if (postDel != null) {
			postDel = postDel.getRightChild();
		}
		// Añadir al nodo predecesor rama derecha con el nodo siguiente a eliminar
		prevDel.setRightChild(postDel);	
	}

	@Override
	public void setParent(GTree<E> t) {
		// TODO Auto-generated method stub	
	}

}
