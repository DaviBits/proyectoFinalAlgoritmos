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

public class GrafoDirigidoAciclico <T>{

    private int [][] matriz;//matriz de adyacencia
    private int numVertices;
    private HashMap<T, Integer> verticeToIndex;
    private int proxIndice;

    public GrafoDirigidoAciclico(int n){
        this.numVertices=n;
        matriz= new int[numVertices][numVertices];
        verticeToIndex= new HashMap<>();
        proxIndice=0;

        for(int i=0; i<=numVertices-1; i++){
            for(int j=0; j<=numVertices-1; j++){
                matriz[i][j]=0;
            }
        }
    }


}
