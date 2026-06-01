package Logica;


public class Cola <T> {
    private T[] cola;
    private int inicio;
    private int fin;
    private int max;


    public Cola(int max){
        this.max=max;
        this.inicio=-1;
        this.fin=-1;
        cola= (T[]) new Object[max];
    }


    public Cola(){
        this(10);
    }


    public void insertar(T obj){
        if(fin<max-1){
            fin++;
            cola[fin]=obj;
            if(fin==0){
                inicio=0;
            }
        }else{
            System.out.println("desbordamiento");
        }
    }


    public T eliminar(){
        T ultimo=null;


        if(inicio!=-1){
            ultimo=cola[inicio];


            if(fin==inicio){//se vacio la pila
                inicio=-1;
                fin=-1;
            }else{
                inicio++;
            }
        }else{
            System.out.println("subdesbordamiento");
        }
        return ultimo;
    }


    public T verUltimo(){
        T ultimo=null;
        if(fin!=-1){
            ultimo=cola[fin];
        }
        return ultimo;
    }


    public boolean colaLlena(){
        return inicio==0&&fin==max-1;
    }


    public boolean colaVacia(){
        return inicio==-1&&fin==-1;
    }

    public String toString(){
        StringBuilder impresion = new StringBuilder();
        if(inicio == -1) return "";

        for(int i = inicio; i <= fin; i++){
            impresion.append(cola[i]);
        }
        return impresion.toString();
    }


}
