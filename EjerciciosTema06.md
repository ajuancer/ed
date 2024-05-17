# Ejercicio 1
* Si consideramos el tamaño del problema como n = número de hojas del árbol:
* En cuanto al análisis de Tree:
  * El constructor llama al constructor de secuencia que realiza una operación independiente del tamaño del problema, pues fija el tamaño de la variable size a 0. La otra operación realizada es la asignación de root a null, que también es de coste constante.
  * El método getRoot devuelve el valor de root, operación de coste constante O(1)
  * isLeaf compara root con null, operación de coste constante, y si es valor es distinto de null, se llama a la función getNUmChildren, cuyo coste dependerá de la implementación.
  * isEmpty compara el valor de root con null, por lo que la función es de coste constante O(1)
  * clear() llama a Collection.clear(), que fija el valor de size a cero, y por tanto es de coste independiente del tamaño del problema. También se fija root a null, de coste constante, y por tanto el método resulta de coste constante O(1)
  * El resto de funciones son asbtractas y su coste dependerá de la implementación
* El cuanto al análisis de GTree:
  * El constructor llama al constructor de Tree, de coste constante, y crea la lista children, operación de coste constante. El constructor por tanto sigue siendo de coste O(1)
  * setRoot fija el valor de la variable root, por tanto es de coste constante O(1)
  * getChild devuelve el valor almacenado en la variable children, es por tanto de orden constante O(1)
  * addChild inserta en la posición indicada un elemento en la lista child. El coste asintótico temporal peor de insertar un elemento en la lista se da cuando la posición no es uno, en cuyo caso se utiliza la función de Secuenca getNode qye itera hasta alcanzar la posición deseada que en el caso peor será n. Por tanto el coste de addChild es O(n)
  * removeChild llama a la función de Lista remove(). El caso peor, cuando la posición a eliminar coincide con el tamaño del problema, se llama a getNode(n), función de Sequence de coste asintótico temporal peor O(n)
  * size() en el caso peor debe generar un iterador. El iterador llega hasta el último elemento de la lista, y cuando actualizando el valor del tamaño sumándole el tamaño del elemento, lo que resulta en una llamada recursiva a size. Puesto que se trata de una reducción mediante sustracción, y solo hay una llamada a la función, el valor de a = 1, b = 1, y el coste viene determinado por O(n*C_nr(n)+C_b(n)). El caso base tiene coste constante, y el caso recursivo también. Por tanto el coste asintótico temporal peor de size() es O(n)
  * clear() llama a Tree.clear() que ya hemos analizado, es de coste constante, además de llamar a Sequence.clear() al limpiar la lista childre. Las operaciones de Sequence.clear() también son independientes del tamaño del problema, por lo que el coste de la función es constante O(1)
  * contains() en el caso peor debe generar un iterador que compara elemento a elemento. En el caso peor, llegará hasta el elemento último, n, llamando a contains() de manera recursiva. Por tanto, al igual que size, se trata de una función recursiva con a=1, B=1, C_cn y C_b de coste O(1), y por tanto contains será de coste O(n)
  * getNumChildren() calcula el tamaño de la Lista children usando List.size(), que es un método de coste constante al devolver el valor de la variable definida en Collection size. Es por tanto de coste O(1)
  * getFanOut() es el caso peor también debe crea un iterador que, hasta el último elemento, llama recursivamente a getFanOut para ir actualizando el valor de la variable fOut con el mayor de los tamaños de cada lista. Se trata, al igual que size() y contains() de una función de coste O(n) por ser una función recursiva por sustracción de a=1, b=1, y C_b y C_nr de O(1), dando O(n) como coste final.
  * La función getHeight() también es de coste O(n), siguiendo el mismo razonamiento, con la diferencia de que en este caso la variable de interés, height, no va almacenando el valor máximo que deuelve getNumChildren(), sino que suma uno en cada nivel del árbol.
  * El coste de iterator dependerá de los casos, puesto que antes de esta estructura, las funciones son de coste constante.
    * Para preorder y postorder, el coste será el mismo puesto que solo varia el momento en el que se encola la raiz. Analizando las funciones vemos que se crea un iterador que avanza hasta llegar al último elemento. En cada iteración, llama a la propia función con el siguiente valor de la lista. Por tanto, iterator() en los casos de preordes y postorder, tiene un coste O(n) al ser una recursividad por sustracción de a, b=1, C_b,C_nr=O(1).
    * El el caso de breadthLR(), se crea una cola auxiliar a la que le añaden los árboles. La cola auxiliar se va desencolando, y de cada elemento desencolado se encola su raíz y se crea un iterador con sus hijos. Esto se realiza hasta que la cola auxiliar esté vacia, y puesto que almacena todo el árbol, coincide con el tamaño del problema. El iterador obtenido se recorre hasta el último elemento, en cada iteración se encolan los siguientes elementos (es decir, en la primera iteración, se encolarán n-1 elementos). Nos encontramos por tanto con un while dentro de un while: n * máx{O(1), (n-1) * máx{O(1), O(1)}}, es decir O(n^2). El coste peor de iterator() será el peor de entre preorder y postorder, O(n), y breadthLR, O(n^2), por lo que será O(n^2)
* En cuanto al análisis de BTree
  * El constructor es de coste constante por llamar al constructor de Tree y fijar el valor de leftChild y rightChild a null, operaciones de coste constante.
  * getRightChild y getLeftChild devuelven también el valor de la variable a la que hacen referencia, por lo que son de coste constante.
  * Lo mismo ocurre con setRoot por asignar a root el valor pasado como atributo.
  * Los setters de right y leftChild tendrán también coste constante por ser operaciones independientes del tamaño del problema
  * Este razonamiento mencionado con getter y setter también se extiende a removeLeftChild y removeRightChild
  * size() es una función recursiva en el caso peor -cuando el árbol no está vacio-. Al ser recursiva con C_b y C_cr = O(1), y tener a=2, b=1, el coste es O(2^n)
  * clear() es una función independiente del tamaño del problema, por ser una función que asigna el valor a variables accesibles de manera directa sin necesidad de tener que crear iteradores.
  * contains() es una función recursiva por sustracción con a=2, b=1, C_nr, C_b=O(1). El coste es O(2^n).
  * getNumChildren() es una función que pasa a ser de coste constante al sumar directamente uno por cada rama de las dos posibles si ésta es no nula. 
  * getFanOut() y getHeight(), al igual que contains() son funciones recursivas con a=2, b=1, C_nr, C_b=O(1). El coste es O(2^n).
  
  
El método iterator no me veo capaz de analizarlo, de igual manera que la intuición me indica que últimas funciones analizadas no pueden tener ese coste teniendo en cuenta que para ároles generales he sugerido que son funciones de coste constante.

Espero las correciones,