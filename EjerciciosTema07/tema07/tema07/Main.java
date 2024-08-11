package tema07;

public class Main {
	
    public static void main(String[] args) {
        // Creamos un árbol de enteros
        IBSTree tree = new IBSTree();
        
        // Establecemos la raíz
        tree.setRoot(30);
        
        // Creamos el primer nodo hijo con valor 5
        IBSTree child1 = new IBSTree();
        child1.setRoot(33);
        
        // Creamos dos nodos hijos hoja con valores 3 y 7
        IBSTree leaf1 = new IBSTree();
        leaf1.setRoot(31);
        
        IBSTree leaf2 = new IBSTree();
        leaf2.setRoot(35);
        
        // Añadimos los nodos hoja al primer nodo hijo
        child1.setLeftChild(leaf1);
        child1.setRightChild(leaf2);
        
        // Creamos el segundo nodo hijo con valor 15
        IBSTree child2 = new IBSTree();
        child2.setRoot(15);
        
        // Añadimos los nodos hijos al árbol
        tree.setRightChild(child1);
        tree.setLeftChild(child2);
        
        // Obtenemos el máximo valor de las hojas
        tree.view(BTreeIF.IteratorModes.PREORDER);
        
        Integer maxLeafValue = tree.min();
        
        if (maxLeafValue != null) {
            System.out.println("El máximo valor de las hojas es: " + maxLeafValue);
        } else {
            System.out.println("No hay hojas en el árbol.");
        }
        
        tree.view(BTreeIF.IteratorModes.INORDER);
        System.out.println();        
        tree.view(BTreeIF.IteratorModes.BREADTH);
        System.out.println();
        System.out.println(tree.size());
        System.out.println(tree.getHeight());
        System.out.println(tree.getFanOut());
        
        
        
        
        
    }
}
