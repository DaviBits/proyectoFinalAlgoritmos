package Logica;


import java.util.Scanner;


public class DAGTextUI {
    private Scanner sc;
    private GrafoDirigidoAciclico <String> grafo;


    public DAGTextUI(){
        sc= new Scanner(System.in);
        grafo=null;
        imprimirTitulo();
        mostrarMenu();
    }


    public void mostrarMenu(){
        int opc=0;
        while (opc!=14){
            System.out.println("INGRESE QUE OPCION QUIERE REALIZAR");


            System.out.println("[1] Crear un nuevo grafo");
            System.out.println("[2] Insertar vértice");
            System.out.println("[3] Insertar arista");
            System.out.println("[4] Ver estructura");
            System.out.println("[5] Topological Sort");
            System.out.println("[6] Grado de entrada de un vértice");
            System.out.println("[7] Grado de salida de un vértice");
            System.out.println("[8] Cuántas aristas hay");
            System.out.println("[9] ¿Son adyacentes?");
            System.out.println("[10] ¿Están conectados?");
            System.out.println("[11] ¿Tiene ciclos?");
            System.out.println("[12] Eliminar aristas");
            System.out.println("[13] capturar un grafo");
            System.out.println("[14] Salir");


            opc=sc.nextInt();
            procesarInstruccion(opc);
        }


    }


    public void procesarInstruccion(int opc){
        if(opc!=13&&opc!=1&&opc!=14&&grafo==null){
            System.out.println("aun no hay grafo creado");
            return;
        }
        switch (opc){
            case 1:
                System.out.println("ingrese cuantos vertices tendra el grafo:");
                int vertices= sc.nextInt();
                grafo= new GrafoDirigidoAciclico<>(vertices);
                System.out.println("grafo creado con exito");
                break;
            case 2:
                System.out.println("ingrese que vertice quiere ingresar: ");
                grafo.insertarVertice(sc.next());
                break;
            case 3:
                System.out.println("Ingrese el vertice origen: ");
                String origen= sc.next();
                System.out.println("ingrese el vertice destino: ");
                String destino=sc.next();
                grafo.insertarArista(origen, destino);
                break;
            case 4:
                System.out.println( grafo.mostrarEstructura());
                break;
            case 5:
                System.out.println(grafo.topologicalSort());
                break;
            case 6:
                System.out.println("Ingresa que vertice quieres conocer el grado de entrada: ");
                System.out.println("Grado de entrada: " + grafo.gradoDeEntrada(sc.next()));
                break;
            case 7:
                System.out.println("Ingresa que vertice quieres conocer el grado de salida: ");
                System.out.println("Grado de salida: " + grafo.gradoDeSalida(sc.next()));
                break;
            case 8:
                System.out.println("hay "+grafo.cuantasAristasHay()+" aristas");
                break;
            case 9:
                System.out.println("ingresa el vertice origen: ");
                String vertice1 = sc.next();
                System.out.println("ingresa el vertice destino: ");
                String vertice2= sc.next();
                System.out.println(grafo.adyacente(vertice1, vertice2));
                break;
            case 10:
                System.out.println("ingresa el vertice origen: ");
                String ori = sc.next();
                System.out.println("ingresa el vertice destino: ");
                String des= sc.next();
                System.out.println(grafo.conectados(ori, des));
                break;
            case 11:
                System.out.println("tiene ciclos: "+grafo.tieneCiclos());
                break;
            case 12:
                grafo.eliminarAristas();
                System.out.println("aristas eliminadas");
                break;
            case 13:
                System.out.println("ingrese cuantos vertices tendra el grafo:");
                int verticesB= sc.nextInt();
                grafo= new GrafoDirigidoAciclico<>(verticesB);
                System.out.println("grafo creado con exito");
                for(int i=0;i< verticesB; i++){
                    System.out.println("ingresa el vertice "+i+": ");
                    grafo.insertarVertice(sc.next());
                }
                System.out.println("vertices caprurados con exito");
                System.out.println(grafo.mostrarEstructura());
                System.out.println("Cuantas aristas desea ingresar?");
                int numAristas=sc.nextInt();
                if(numAristas<=0||numAristas>(verticesB*verticesB)){
                    System.out.println("cantidad de aristas invalida");
                    break;
                }else{
                    for(int i=0; i<numAristas; i++){
                        System.out.println("Ingrese el vertice origen: ");
                        String verticeOrigen= sc.next();
                        System.out.println("ingrese el vertice destino: ");
                        String verticeDestino=sc.next();


                        if (grafo.insertarArista(verticeOrigen, verticeDestino)){
                            System.out.println("arista"+ verticeOrigen+" -> "+verticeDestino+" creada");
                        }
                    }
                }
                break;
            default:
                break;


        }
        System.out.println("=========================================================");
    }


    private void imprimirTitulo(){
        System.out.println("  ___          _                            _            _         ");
        System.out.println(" / _ \\ _ __ __| | ___ _ __   __ _ _ __ ___ (_) ___ _ __ | |_ ___  ");
        System.out.println("| | | | '__/ _` |/ _ \\ '_ \\ / _` | '_ ` _ \\| |/ _ \\ '_ \\| __/ _ \\ ");
        System.out.println("| |_| | | | (_| |  __/ | | | (_| | | | | | | |  __/ | | | || (_) |");
        System.out.println(" \\___/|_|  \\__,_|\\___|_| |_|\\__,_|_| |_| |_|_|\\___|_| |_|\\__\\___/ ");
        System.out.println("                                                                    ");
        System.out.println("|_   _|__  _ __   ___ | | ___   __ _(_) ___ ___                   ");
        System.out.println("  | |/ _ \\| '_ \\ / _ \\| |/ _ \\ / _` | |/ __/ _ \\                  ");
        System.out.println("  | | (_) | |_) | (_) | | (_) | (_| | | (_| (_) |                 ");
        System.out.println("  |_|\\___/| .__/ \\___/|_|\\___/ \\__, |_|\\___\\___/                  ");
        System.out.println("          |_|                   |___/                              ");
        System.out.println("=============== POR DAVID GARCIA Y ABRAHAM MORENO ===========================");
    }
}
