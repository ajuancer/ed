# Pregunta 1
Las funciones de `Colletion` públicas de tipo no abstracto son las cuatro siguientes:

```java
	public Collection () { size = 0; }
	
	public int size () { return size; }
	
	public boolean isEmpty () { return size == 0; }
	
	public void clear () { size = 0; }
```
En todas ellas, la función realiza una operación básica que no depende del tamaño de la colección, y por tanto, se tratan de funciones de orden constante, O(1).

# Pregunta 2
Las operaciones públicas de `Sequence` son las siguientes:

## Constructor
```java
	public Sequence () { super(); this.firstNode = null; }
```

El constructor llama al constructor de `Collection`, que al realizar una operación de asignación y esta ser independiente del tamaño, es de O(1). Además realiza otra asignación, que también se trata de una operación básica, y por tantp de orden constante. En consecuencia, 

![a](q1.png)

## Constructor de tipo copia
```java
	public Sequence (Sequence<E> s) {
		this ();
		
		if ( !s.isEmpty()) {
			this.size = s.size();
			NodeSequence nodeS = s.getFirstNode();
			NodeSequence pNode = new NodeSequence(nodeS.getValue());
			this.firstNode = pNode;
			while (nodeS.getNext() != null) {
				nodeS = nodeS.getNext();
				NodeSequence nNode = new NodeSequence(nodeS.getValue());
				pNode.setNext(nNode);
				pNode = nNode;
			}
		}
	}
```

El constructor copia realiza 
1. en primer lugar una llamada al constructor anterior, que ya hemos calculado, tiene O(1). 
2. A continuación nos encontramos con una estructura if-else, por lo que su O será la mayor de entre la condición y el contenido ejecutado en el caso true. 
   1. Respecto a la condición, se trata de la negación del resultado de la función isEmpty(), que ya hemos calculado como O(1).
   2. Respecto al contenido: 
      1. se tratan, en las cuatro primeras líneas, de asignaciones con llamadas a funciones cuyo O ya hemos calculado como O(1). 
      2. Después nos encontramos con una estructura de tipo while, por lo que su O será O(v(n)*max{O(C_e), O(C_s)}), siendo v(n) el tamaño del problema, C_e la condición y C_s el contenido dentro del while. 
         1. El tamaño del problema n es el número de elementos de la secuencia
         2. La condición es una operación de tamaño independiente de n, O(1).
         3. Dentro del while se realizan cuatro asignaciones, con O total suma de cada O. Al ser operaciones básicas el coste será constante, O(1), y por tanto, O(1)
      3. Por tanto, el O total será O(n)*max{O(1), O(1)}, es decir, O(n)
3. Por tanto, tenemos que max{O(1), max{O(1), O(1)+O(n)}} = O(n)

## Clear
```java	
	public void clear () { super.clear(); this.firstNode = null; }
```

Clear llama a la función de sequence, que realiza una operación de asignación y es de orden constante. Después realiza la asignación firstNode = null, por lo que O(1)+O(1)=O(1)

## Contains
```java	
	public boolean contains(E e) {
		NodeSequence node = this.firstNode; 	// O(1)
		
		while(node != null) {					// O(1)
			E next = node.getValue();			// O(1)
			if (next.equals(e)) { return true; }// O(1)
			node = node.getNext();				// O(1)
		}
		return false;							// O(1)
	}
```
Las operaciones básicas de comparación son de coste constante. La sentencia while será de orden v(n)*max{C_e, C_s}: n*max{O(1), O(1)} = O(n). La suma de órdenes de la función sería la orden mayor, O(n).
