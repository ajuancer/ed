# Ejercicio 1
Si se trata de un árbol binario de búsqueda, el máximo se encontrará en la hoja más derecha del árbol, y el valor mínimo en la hoja más izquierda. Si se tratara de un árbol binario de búsqueda autoequilibrado, el coste de la búsqueda será de orden log(n), siendo n el número de elementos. Si es un árbol binario de búsqueda pero no es autoequilibrado; o si se trata de un árbol binario, en el peor de los casos el coste de la búsqueda será de O(n) siendo n el número de nodos del árbol, puesto que podría tratarse de un árbol que solo añade nuevos nodos como rama del último elemento añadido. Precísamente en este caso sería necesario recorrer todos los nodos del árbol.

# Ejercicio 2
El coste asintótico peor de una función que busque los números dentro de un intervalo cerrado en un árbol binario de búsqueda, suponiendo que sea autoequilibrado, ocurre cuando todos los nodos del árbol cumplen la codición y por tanto se debe recorrer todo el árbol. En el caso de mi implementación, las únicas operaciones que no son de orden constante O(1) son la operación de insertar en la lista que se va a devolver, O(n), siendo n el número de nodos del árbol, y el while, que también es de coste O(n), con n el número de nodos del árbol al ser O(max(iniciación, v(n)*max(cuerpo, requisito, acción postcuerpo))). La suma de coste indica que el método tiene coste O(n), siendo n el número de nodos del árbol.

# Ejercicio 3
De acuerdo con mi implementación:
```java
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
```
Se trata de una implementación recursiva en la que se comienza con la raíz del árbol que se quiere comprobar. En cada llamada, se comprueba si es vacio, siendo isEmpty(), que es un método de coste constante independiente del tamaño del problema n=número de nodos del árbol.
En el siguiente if se comprueba que el nodo evaluado sea mayor o igual al valor mínimo permitido, y menor o igual al valor máximo permitido. Estos valores permitidos se determinan de manera recursiva al llamar a isBSTree, en el caso del hijo izquierdo se indica que el valor máximo debe ser el valor de la raíz, y en el caso del hijo derecho se indica que debe su valor mínimo debe ser igual al valor del nodo.

Las operaciones de comparación, getRoot, y getLeftChild y getRightChild son también de coste constante independiente del problema.

Teniendo en cuenta que se trata de recursividad por sustracción, y que se llama a la función dos veces por nodo, el valor de a=2. Puesto que en cada iteración se avanza un nodo, el valor de b=1; y por tanto, el coste será O(n) \in O(a^{n div b} * (C_{nr}(n) + C_{b}(n))) = 2^n.

# Ejercicio 4
El recorrido en preorden visita raíz -> hijo izq -> hijo dcho, por lo que podríamos generar unívocamente el árbol binario asociado a él: el primer elemento es la raíz, los siguientes menores al valor raíz son árbol izq, y los siguientes con valor mayor a raíz son árbol dcho. 

El recorrido en inorden no permite generar unívocamente un árbol binario puesto que inorder devuelve todos los nodos del árbol ordenados en órden creciente y no podríamos determinar que nodo pertenecía al hijo izq, cuál era raíz y cuál era hijo dcho.

El recorrido en postorder sí permite generar un único árbol asociado puesto que recorre primero árbol izquierdo, luego derecho y luego la raíz. Usando el valor de la raíz dividimos los nodos obtenidos anteriormente en los de árbol derecho y árbol izquierdo.

La implementación es la siguiente:
```java
	public BTree(List<BTreeIF<E>> list, BTreeIF.IteratorModes mode) {
		super();
		switch (mode) {
			case BREADTH:
				break;
			case INORDER:
				break;
			case POSTORDER:
				BTreeIF<E> t = BTreeFromPostorderList(list, 1, list.size);
				this.leftChild = t.getLeftChild();
				this.rightChild = t.getRightChild();
				this.root = t.getRoot();
				break;
			case PREORDER:
				BTreeIF<E> t1 = BTreeFromPreorderList(list, 1, list.size);
				this.leftChild = t1.getLeftChild();
				this.rightChild = t1.getRightChild();
				this.root = t1.getRoot();
				break;
			case RLBREADTH:
				break;
			default:
				break;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private BTreeIF<E> BTreeFromPreorderList(List<BTreeIF<E>> list, Integer start, Integer end) {
		if (start > end) {
			return null;
		}
		
		// En preorden se recorre primero el nodo raíz, después el subarbol izq 
		// y finalmente el subárbol dcho
		BTreeIF<E> root = list.get(1);
		
		// El subárbol izq serán los valores menores a la raíz
		int i;
		for (i = start +1; i <= end; i++) {
			if (((Comparable<E>) list.get(i).getRoot()).compareTo(root.getRoot()) > 0) {
				break;
			}
		}
		
		root.setLeftChild(BTreeFromPreorderList(list, start + 1, i - 1));
		root.setRightChild(BTreeFromPreorderList(list, i, end));
		
		return root;
	}
	
	
	@SuppressWarnings("unchecked")
	private BTreeIF<E> BTreeFromPostorderList(List<BTreeIF<E>> list, Integer start, Integer end) {
		if (start > end) {
			return null;
		}
		
		// En postorder se recorre primero el subarbol izq, después el dcho y
		// finalmente el nodo raíz
		BTreeIF<E> root = list.get(list.size);
		
		// El subárbol izq serán los valores menores a la raíz
		int i;
		for (i = start; i <= end - 1; i++) {
			if (((Comparable<E>) list.get(i).getRoot()).compareTo(root.getRoot()) > 0) {
				break;
			}
		}
		
		root.setLeftChild(BTreeFromPostorderList(list, start, i - 1));
		root.setRightChild(BTreeFromPostorderList(list, i, end - 1));
		
		return root;
	}
```
El tamaño del problema es el número de nodos n.

El cada iteración, el método más costoso es get(), de coste O(n). El bucle for implica que se itera n veces, por lo que el coste es max{ini; v(n)*max{S, e, inc}} = max{1, n*{n,1,1}} = n^2.

Al tratarse de una función recursiva con dos llamadas por vuelta, a=2, y puesto que se evalúa un nodo por vuelta, b=1. Por tanto, O(n)=2^n * n^2.


