package Logica;

import java.util.*;


/**
 * Clase usada para modelar un Grafo dirgido aciclico
 * Utiliza una matriz de adyacencia para almacenar las relacion/ modelar el grafo
 * Utiliza el HashMap para almacenar la relacion entre los indices de la matriz y los vertices
 *
 * @param <T> tipo de dato de los vertices
 * @author David de Jesus Garcia Rodriguez, Jesus Abraham Moreno Flores
 */


public class GrafoDirigidoAciclico <T> {


    private int[][] matriz;//matriz de adyacencia
    private int numVertices;
    private HashMap<T, Integer> verticeToIndex;
    private int proxIndice;


    public GrafoDirigidoAciclico(int n) {
        this.numVertices = n;
        matriz = new int[numVertices][numVertices];
        verticeToIndex = new HashMap<>();
        proxIndice = 0;


        for (int i = 0; i <= numVertices - 1; i++) {
            for (int j = 0; j <= numVertices - 1; j++) {
                matriz[i][j] = 0;
            }
        }
    }


    /**
     *
     * @param i vertice del que quermos conocer cuantas aristas inciden en el
     * @return el grado de entrada del vertice i
     * @throws IllegalArgumentException si el vertice i no se encuentra en el conjunto de datos delimitado por n-1
     */
    public int gradoDeEntrada(T i) {
        if (!verticeToIndex.containsKey(i)) {
            throw new IllegalArgumentException("Vertice fuera de rango");
        }
        int indiceVertice = verticeToIndex.get(i);
        int contadorSalidas = 0;
        for (int k = 0; k <= numVertices - 1; k++) {
            if (matriz[k][indiceVertice] == 1) {
                contadorSalidas++;
            }
        }
        return contadorSalidas;
    }

    /**
     *
     * @param i vertice del que quermos conocer cuantas aristas salen de el
     * @return el grado de salida del vertice i
     * @throws IllegalArgumentException si el vertice i no se encuentra en el conjunto de datos delimitado por n-1
     */
    public int gradoDeSalida(T i) {
        if (!verticeToIndex.containsKey(i)) {
            throw new IllegalArgumentException("Vertice fuera de rango");
        }
        int indiceVertice = verticeToIndex.get(i);
        int contadorSalidas = 0;
        for (int k = 0; k <= numVertices - 1; k++) {
            if (matriz[indiceVertice][k] == 1) {
                contadorSalidas++;
            }
        }
        return contadorSalidas;
    }


    /**
     * recorre la matriz de adyacencia para contar cuantas aristas hay en el grafo
     *
     * @return la cantidad de aristas del grafo
     */
    public int cuantasAristasHay() {
        int contadorAristas = 0;
        for (int i = 0; i <= numVertices - 1; i++) {
            for (int j = 0; j <= numVertices - 1; j++) {
                if (matriz[i][j] == 1) {
                    contadorAristas++;
                }
            }
        }
        return contadorAristas;
    }


    /**
     *
     * @param i vertice origen
     * @param j vertice destino
     * @return true si hay un arista desde i hacia J
     * @throws IllegalArgumentException si i o j estan fuera del conjunto de n-1
     */
    public boolean adyacente(T i, T j) {
        if (!verticeToIndex.containsKey(i) || !verticeToIndex.containsKey(j)) {
            throw new IllegalArgumentException("vertice fuera de rango");
        }
        int fuente = verticeToIndex.get(i);
        int destino = verticeToIndex.get(j);


        return matriz[fuente][destino] == 1;
    }


    /**
     *
     * @param i vertice origen
     * @param j vertice destino
     * @return true si hay conexion directa de i hacia j
     * @return true si hay un caso reflexivo
     * @return false si no estan conectados
     * @throws IllegalArgumentException si i o j estan fuera del rango n-1
     */
    public boolean conectados(T i, T j) {
        if (!verticeToIndex.containsKey(j) || !verticeToIndex.containsKey(i)) {
            throw new IllegalArgumentException("vertice fuera de rango");
        }
        if (i.equals(j)) return true;//caso ciclico


        Cola<T> colaAux = new Cola<>(numVertices);
        HashSet<T> visitados = new HashSet<>();
        colaAux.insertar(i);
        visitados.add(i);


        while (!colaAux.colaVacia()) {
            T verticeActual = colaAux.eliminar();
            int idx = verticeToIndex.get(verticeActual);
            Iterator<Map.Entry<T, Integer>> iterator = verticeToIndex.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<T, Integer> entry = iterator.next();
                if (matriz[idx][entry.getValue()] == 1) {
                    T vecino = entry.getKey();
                    if (vecino.equals(j)) return true;
                    if (!visitados.contains(vecino)) {
                        visitados.add(vecino);
                        colaAux.insertar(vecino);
                    }
                }
            }


        }


        return false;
    }


    /**
     * Verifica si el grafo tiene ciclos usando DFS
     * Recorre todos los vertices del grafo y llama a un metodo auxiliar para buscar ciclos
     *
     * @return true si el grafo tiene ciclos, false si no tiene
     */
    public boolean tieneCiclos() {
        HashSet<T> visitados = new HashSet<>();
        HashSet<T> enCaminoActual = new HashSet<>();


        for (Map.Entry<T, Integer> entry : verticeToIndex.entrySet()) {
            T vertice = entry.getKey();
            if (!visitados.contains(vertice)) {//exploramos vertices no procesados
                if (tieneCicloAux(vertice, visitados, enCaminoActual)) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     *
     * @param vertice        vertice que se esta explorando
     * @param visitados      conjunto de vertices ya procesados completamente
     * @param enCaminoActual conjunto de vertices en la ruta de recursion actual
     * @return true si detecta un ciclo
     * @return false si no detecta un ciclo
     */
    private boolean tieneCicloAux(T vertice, HashSet<T> visitados, HashSet<T> enCaminoActual) {
        visitados.add(vertice);//indicamos que el vertice ya fue procesado/explorado
        enCaminoActual.add(vertice);//lo agregamos al recorrido actual


        int idx = verticeToIndex.get(vertice);
        Iterator<Map.Entry<T, Integer>> iterator = verticeToIndex.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<T, Integer> entry = iterator.next();
            if (matriz[idx][entry.getValue()] == 1) {//hay arista en este vecino
                T vecino = entry.getKey();
                if (enCaminoActual.contains(vecino)) return true;//vecino ya en el recorrido, por lo tanto hay un ciclo
                if (!visitados.contains(vecino)) {
                    if (tieneCicloAux(vecino, visitados, enCaminoActual)) return true;
                }
            }
        }


        enCaminoActual.remove(vertice);
        return false;
    }


    /**
     *
     * @return String de la matriz de adyacencia
     */
    public String mostrarEstructura() {
        String[] indiceAlVertice = new String[numVertices];
        for (Map.Entry<T, Integer> entry : verticeToIndex.entrySet()) {
            indiceAlVertice[entry.getValue()] = entry.getKey().toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("  ");
        for (int i = 0; i < numVertices; i++) {
            sb.append(indiceAlVertice[i]);
            sb.append(" ");
        }
        sb.append("\n");


        for (int i = 0; i <= numVertices - 1; i++) {
            sb.append(indiceAlVertice[i]);
            sb.append(" ");
            for (int j = 0; j <= numVertices - 1; j++) {
                sb.append(matriz[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}