# Ejercicio 1
El caso no recursivo se da en n=0, en cuyo caso el programa devuelve el valor 1.

El caso recursivo se da en n!=0. En esta situación el argumento de la llamada recursiva sería n-1, por lo que, partiendo de la precondición n>=0, n-1 converge al caso n==0. El resultado final se obtendrá sumando los resultados obtenidos deesde el último caso (n==0), hasta el caso inicial, de ahí que las operaciones se puedan almacenar en forma de stack.

# Ejercicio 2
El caso no recursivo se da en (n=0 || n=1). En esta situación se devuelve el valor 1.

El caso recursivo se da en n>1. En este casi el valor devuelto es fibonacci(n-1) + fibonacci(n-2).

No sería eficiente por que no se cumple una de las cuatro reglas de la recursividad, concretamente la regla del interés compuesto. Además, se calcularían valores redundantes, pues fibonacci(n-2) se calculará tanto en fibonacci(n) como en fibonacci(n-1).

Un procedimiento más eficiente podría utilizar un bucle en lugar de recursión:
```java
public static int fibonacci(int n) {
    if (n<=1)
        return 1;

    int prev = 1;
    int current = 1;
    if (int i = 2; i <= n; i++){
        int tmp = prev + current;
        prev = current;
        current = tmp;
    }
    return current;
}
```

Otra aproximación podría ser una función recursiva que tiene en cuenta una lista con los valores calculados previamente. 
```java
// Array con espacio suficiente para almacenar todos los valores de la serie hasta el valor a calcular
int[] memo = new int[n + 1]; 

// Llamada a la función recursiva
fibonacci(n, memo);


// @pre 0 <= n
public static int fibonacci(int n, int[] memo) {
    // Para n=0 y n=1, devolver 1 (caso no recursivo)
    if (n <= 1)
        return 1;
    
    // Para un elemento ya calculado, devolver el elemento (caso no recursivo)
    if (memo[n] != 0)
        return memo[n];
    
    // Si no, se trata de un nuevo elemento (caso recursivo)
    memo[n] = fibonacci(n - 1, memo) + fibonacci(n - 2, memo);
    return memo[n];
}
```
Lo cierto es que no me parece muy buen método porque no respeta la regla mencionada al principio (regla de interés compuesto), aunque si es cierto que soluciona el problema del enunciado, puesto que cada valor solo se calcula una vez. Si se vuelve a necesitar, simplemente se extrae de la lista (segundo caso no recursivo) en lugar de llegar al caso recursivo.

# Ejercicio 3
No se trata de una función recursiva por lo que no podemos diferenciar casos recursivos y no recursivos.
Podríamos transformar la función en recursiva de la siguiente forma:
```java
public static int exponente(int a, int b) {
  if (b == 0) {
    return 1;
  } else {
    return exponente(a, b / 2) * exponente(a, b / 2);
  }
}
```
Pero tendríamos, como apuntan otros compañeros, el mismo problema que en el ejercicio 3, al estar llamando dos veces a la función dentro de la misma iteración.

Por este motivo, y de nuevo, similar al procedimiento de almacenar la lista de la serie de fibonacci, en la siguiente función almaceno los datos en el caso b es par:
```java
public static int exponente(int a, int b) {
    if (b == 0) {
        return 1;
    }

    if (b % 2 == 0) {
        int temp = exponente(a, b / 2);
        return temp * temp;
    }

    return a * exponente(a, b - 1);
}
```

# Ejercicio 4
El primer caso suma repetidamente el 2 n-1 veces y finalmente, sumará 2, lo que se podría resolver de manera más eficiente ejecutando `2*n` teniendo como precondición 0<=n.

El segundo caso calcula e^(n-1) + 1, y al igual que el procedimiento anterior, se puede llegar a superar el tamaño máximo de pila. Resultaría más sencillo utilizar una función no recursiva que usara la función `Math.pow`: `Math.pow(e, (n-1))+1`.

El tercer caso, además del problema ya mencionado, no convergerá hacia el caso no recursivo si n<3 o n es par. En cuanto a su operación, en único caso que se llega a alcanzar el caso no recursivo (3<=n && n%2!=0), multiplica 2 n-2 veces hasta que n valga 3, sumando uno al resultado. De nuevo, se puede resumir en un procedimiento no recursivo: `Math.pow(2, (n-3)/2)+1`

El cuarto caso presenta los mismos límites que el primer y segundo apartado. El resultado obtenido será cero siempre que 0 <= n.

En el quinto caso no se alcanza nunca un caso no recursivo, como sucedía en el tercer caso. En este caso no podemos calcular el resultado esperado puesto que no se define ningún caso recursivo.

# Ejercicio 5
La función que se nos pide definir usando las funciones ya existentes `isEmpty`, `first` y `rest` sería:
```java
public static int sumaLista(List<Integer> lista) {
  if (lista.isEmpty()) {
    return 0;
  } else {
    return lista.first() + sumaLista(lista.rest());
  }
}
```

# Ejercicio 6
De acuerdo a las funciones definidas, se trata de crear una función recursiva que sume todos los nodos:
```java
public static int sumaArbol(BinaryTree<Integer> arbol) {
  if (arbol.isEmpty()) {
    return 0;
  } else {
    int valorRaiz = arbol.root();
    return valorRaiz + sumaArbol(arbol.leftChild()) + sumaArbol(arbol.rightChild());
  }
}
```
Y otra función, no recursiva, que compruebe si el resultado es igual a cero:
```java
public static int sumarArbolCero(BinaryTree<Integer> arbol) {
    return sumaArbol(arbol) == 0;
}
```


