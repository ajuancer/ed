package tema06;

public class Main {
	
    public static void main(String[] args) {
        // Creamos un árbol de enteros
        GTree<Integer> tree = new GTree<>();
        
        // Establecemos la raíz
        tree.setRoot(30);
        
        // Creamos el primer nodo hijo con valor 5
        GTree<Integer> child1 = new GTree<>();
        child1.setRoot(5);
        
        // Creamos dos nodos hijos hoja con valores 3 y 7
        GTree<Integer> leaf1 = new GTree<>();
        leaf1.setRoot(3);
        
        GTree<Integer> leaf2 = new GTree<>();
        leaf2.setRoot(7);
        
        // Añadimos los nodos hoja al primer nodo hijo
        child1.addChild(1, leaf1);
        child1.addChild(2, leaf2);
        
        // Creamos el segundo nodo hijo con valor 15
        GTree<Integer> child2 = new GTree<>();
        child2.setRoot(15);
        
        // Añadimos los nodos hijos al árbol
        tree.addChild(1, child1);
        tree.addChild(2, child2);
        
        // Obtenemos el máximo valor de las hojas
        tree.view();
        
        Integer maxLeafValue = tree.getMax();
        
        if (maxLeafValue != null) {
            System.out.println("El máximo valor de las hojas es: " + maxLeafValue);
        } else {
            System.out.println("No hay hojas en el árbol.");
        }
        
        
        System.out.println(tree.size());
        System.out.println(tree.getHeight());
        System.out.println(tree.getFanOut());
        
        
        
        
        
    }
}
