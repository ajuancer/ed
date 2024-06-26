# Programación recursiva
Reglas de la recursión fundamentales
1. caso base: siempre tiene que haber al menos un caso que se pueda resolver sin recursividad
2. progresión: toda llamada recursiva debe progresar hacia un caso base
3. "Es necesario creer": asuma siempre que la llamada recursiva funciona,
algo que puede llegar a ser difícil de trazar mentalmente.
4. Regla del interés compuesto: nunca duplique el trabajo resolviendo la 
misma instancia de un problema en llamadas recursivas separadas.

La rutina de preparación comprueba la validez de la base antes de llamar a la función recursiva, evitando comprobarlo en la propia función, algo que sería poco eficiente.

La recursión siempre puede ser eliminada utilizando una pila. Esto puede ser necesario para ahorrar espacio.

Un algoritmo divide y vencerás es una implementación de un algoritmo recursivo que por regla general es muy eficiente. La recursión es divide. 

El procesamiento adicional es vencerás.

Los objetos recursivos tienen su paralelismo con estructuras recursivas. Los grafos, con vértices y aristas, forman un ejemplo de estructura recursiva. 
La especialización del grafo denominado árbol también es un ejemplo. 
Las listas no son de forma natural recursivas pero lo pueden ser. La lista orientada como cabeza y cola es un ejemplo de estructura recursiva.

La recursividad puede ser directa o indirecta.

Cualquier definición recursiva operativa debe tener un caso o valor para el que se tiene una alterativa directa no recursiva. Tenemos un caso no recursivo, y un caso recursivo que debe contener una simplificación.

La estrategia de programación se basa en aceptar que la llamada a la recursividad es una simplificación. 



