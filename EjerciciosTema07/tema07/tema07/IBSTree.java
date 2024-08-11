package tema07;


public class IBSTree extends BTree<Integer> implements IBSTreeIF {

	@Override
	public int max() {
		IBSTree iterator = this;
		
		while (!iterator.isLeaf()) {
			iterator = (IBSTree) iterator.getRightChild();
		}
		
		return iterator.getRoot();
	}

	@Override
	public int min() {
		IBSTree iterator = this;
		
		while (!iterator.isLeaf()) {
			iterator = (IBSTree) iterator.getLeftChild();
		}
		
		return iterator.getRoot();
	}
	
	public void view(BTreeIF.IteratorModes mode) {
	    if (isEmpty()) {
	        System.out.println("El árbol está vacío.");
	        return;
	    }

	    IteratorIF<Integer> iterator = this.iterator(mode);
	    
	    while (iterator.hasNext()) {
            System.out.print(iterator.getNext() + " ");
	    }
	}
	
}
