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
Las operaciones básicas de comparación son de coste constante. La sentencia while será de orden `v(n)*max{C_e, C_s}`: `n*max{O(1), O(1)} = O(n)`. La suma de órdenes de la función sería el orden mayor, O(n).

# Pregunta 3
```java
	public List () { super (); }
```

El coste sería O(1) al llamar al constructor de `Sequence`, ya visto en la pregunta 2.

```java
	public List (List<E> s) { super(s); }
```

El coste sería O(n) al llamar al constructor con copia de `Sequence`, ya visto en la pregunta 2.

```java
	public E get (int pos) {
		NodeSequence node = getNode(pos);
		return node.getValue();
	}
```

El coste de get es la suma del orden de return, que es O(1), ya calculado en la pregunta 1, y del orden de `NodeSequence node = getNode(pos)`. El coste será, al ser una asignación, el coste de `getNode(pos)`. Esta función es:

```java
	private NodeSequence getNode(int i) {
		NodeSequence node = firstNode;
		for (int aux = 1; aux < i; aux++) {
			node = node.getNext();
		}
		return node;
	}
```
Que itera hasta que aux alcanza el valor de i. Si definimos el tamaño del problema como `n=número de elementos de la secuencia`, el orden de for será de O(n)*max{O(1), O(1)}, es decir, O(n). Por tanto, el coste de `get()` será O(n).

```java
	public void set (int pos, E e) {
		NodeSequence node = getNode(pos);
		node.setValue(e);
	}
```

La función set tiene orden la suma de getNode(pos) y node.setValue(e), el orden de getNode() es O(n) siguiendo el mismo razonamiento que en la función anterior get. El coste de setValue() es constante, y por tanto el orden de set: `O(n) + O(1) = O(n)`

```java
	public void insert (int pos, E e) {
		NodeSequence newNode = new NodeSequence(e); // O(1)
		if (pos==1) {								// O(1)
			newNode.setNext(this.firstNode);		// O(1)
			this.firstNode = newNode;				// O(1)
		} else {
			NodeSequence prevNode = getNode(pos-1);	// O(n)
			NodeSequence nextNode = previousNode.getNext(); //O(1)
			previousNode.setNext(newNode);	// O(1)
			newNode.setNext(nextNode);		// O(1)
		}
		size++;								// O(1)
	}
```

La función insert contiene una operación constante y una sentencia if, cuya condición tiene coste de orden constante, las operaciones del caso verdadero O(1) y del caso falso O(n). El máximo de estos tres es O(n), y la suma de O(1)+O(n) es O(n), que será el coste asintótico mayor de insert.

# Pregunta 4
Requiere la implementación de pilas que se dará en el siguiente tema
## apartado b

# Pregunta 5
La implementación sin utilizar un iterador podría ser:

```java
	public List<E> invierte(List<E> l) {
		if (l.size < 2) {
			return l;
		}
		List<E> invertedList = new List();
		for (int i=l.size-1; i>=0; i--) {
			invertedList.insert(l.size-i, l.get(i));
		}
		return invertedList;
	}
```

Tanto la condición como las operaciones en caso verdadero del primer if else tienen un coste constante al ser independiente del tamaño del problema (número de elementos de la lista). La siguiente línea también es de coste constante, de igual manera que el return de la última línea. Sólo falta por analizar la estructura for:
- La iniciación es de coste constante al ser l.size independiente del tamaño del problema
- Tanto la comparación como la operación i-- son de coste constante.
- La función insert tiene un coste O(n) (razonado el pregunta 3), y dentro de esta función se llama a l.size(), de coste constante, y l.get(), de coste O(n) (también razonado en la pregunta 3). Por tanto, el coste total del bucle es n*O(n), es decir, O(n^2).
Puesto que la función tiene como coste O(1)+O(1)+O(n^2)+O(1), el orden de la función invierte es O(n^2).

La implementación que utiliza un iterador podría ser:
```java
	public List<E> invierte(List<E> l) {
		if (l.size < 2) {
			return l;
		}
		List<E> invertedList = new NodeSequence();
		NodeSequence iterator = new SequenceIterator(l);
		int index = l.size -1;
		while (iterator.hasNext()) {
			invertedList.insert(index--, iterator.getNext());
		}
		return invertedList;
	}
```

El coste de la estructura ifelse es constante al ser l.size() no dependiente del tamaño del problema. 

Las tres siguientes líneas, y la última, tampoco dependen del tamaño del problema y su coste asintótico temporal mayor es O(1). 

La estructura while se ejecutará hasta que se llega al último elemento de la lista (v(n)=n). hasNext() y getNext() son de coste constante, pero insert es de coste O(n), por lo que el coste de la estructura while es n*O(n)=O(n^2).

El coste de la implementación es O(n^2).

# Pregunta 6

