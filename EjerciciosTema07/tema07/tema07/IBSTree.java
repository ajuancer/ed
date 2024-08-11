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
	
	/**
	 * Devuelve una lista de valores que esté en el intervalo [inf, sup]
	 * @pre !this.isEmpty()
	 * @param inf Límite inferior
	 * @param sup Límite superior
	 * @return Lista con los valores que cumplen las condiciones. Lista vacia si no hay.
	 */
	public ListIF<Integer> beetween(int inf, int sup) {
		IteratorIF<Integer> iterator = this.iterator(BTreeIF.IteratorModes.INORDER);
		int current = iterator.getNext();
		ListIF<Integer> res = new List<Integer>();
		int insertPos = 0;
		// Al hacer un recorrido inorder, en el momento que un valor 
		// sea mayor a sup, todos los siguientes también lo serán.
		while(iterator.hasNext() && current <= sup) {
			if (current >= inf) {
				res.insert(++insertPos, current);
			}
		}
		return res;
	}
	
}
