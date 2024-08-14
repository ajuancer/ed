package tema07;

public class IBTree extends BTree<Integer> {
	boolean isBSTree() {
		return isBSTreeAux(this, null, null);

	}
	
	private boolean isBSTreeAux(IBTree node, Integer min, Integer max) {
		if (node.isEmpty()) {
			return true;
		}
		
		if ((min != null && node.getRoot() < min) || (max != null && node.getRoot() > max)) {
			return false;
		}
		
		boolean left = isBSTreeAux((IBTree) node.getLeftChild(), min, node.getRoot());
		boolean right = isBSTreeAux((IBTree) node.getRightChild(), node.getRoot(), max);
		
		return left && right;
	}
	
}
