/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import domain.list.ListException;
import domain.queue.LinkedQueue;
import domain.queue.QueueException;
import domain.stack.LinkedStack;
import domain.stack.StackException;

/**
 *
 * @author Profesor Gilberth Chaves A <gchavesav@ucr.ac.cr>
 */
public class AdjacencyListGraph implements Graph {
    private int n;
    private Vertex vertexList[];
    private int counter; //contador de vertices
    
    //para los recorridos dfs, bfs
    private LinkedStack stack;
    private LinkedQueue queue;
    
    //Constructor
    public AdjacencyListGraph(int n){
        if(n<=0) System.exit(1);
        this.n = n;
        this.counter = 0;
        this.vertexList = new Vertex[n];
        this.stack = new LinkedStack();
        this.queue = new LinkedQueue();
    }
    
    @Override
    public int size() throws ListException {
        return counter;
    }

    @Override
    public void clear() {
        this.counter = 0;
        this.vertexList = new Vertex[n];
    }

    @Override
    public boolean isEmpty() {
        return counter==0;
    }

    @Override
    public boolean containsVertex(Object element) throws GraphException, ListException {
        if(isEmpty()){
            throw new GraphException("Adjacency List Graph is Empty");
        }
        for (int i = 0; i < counter; i++) {
            if(util.Utility.equals(vertexList[i].data, element))
                return true;
        }
        return false;
    }

    @Override
    public boolean containsEdge(Object a, Object b) throws GraphException, ListException {
        if(isEmpty()){
            throw new GraphException("Adjacency List Graph is Empty");
        }
        return !vertexList[indexOf(a)].edgesList.isEmpty()
                ?vertexList[indexOf(a)].edgesList.contains(new EdgeWeight(b, null))
                :false;
    }

    @Override
    public void addVertex(Object element) throws GraphException, ListException {
        if(counter>=vertexList.length)
            throw new GraphException("Adjacency Matrix Graph is Full");
        vertexList[counter++] = new Vertex(element);
    }

    @Override
    public void addEdge(Object a, Object b) throws GraphException, ListException {
        if(!containsVertex(a)||!containsVertex(b))
            throw new GraphException("Cannot add edge between vertexes ["+a+"] and ["+b+"]");
        vertexList[indexOf(a)].edgesList.add(new EdgeWeight(b, null));
        vertexList[indexOf(b)].edgesList.add(new EdgeWeight(a, null)); //grafo no dirigido
    }
    
    private int indexOf(Object element){
        for (int i = 0; i < counter; i++) {
            if(util.Utility.equals(vertexList[i].data, element)){
                return i; //necesito que retorne la posicion del objeto en la lista de vertices
            }
        }
        return -1;
    }
    
    public void addEdgeWeight(Object a, Object b, Object weight) throws GraphException, ListException {
        if(!containsVertex(a)||!containsVertex(b))
            throw new GraphException("Cannot add edge between vertexes ["+a+"] and ["+b+"]");
        vertexList[indexOf(a)].edgesList.add(new EdgeWeight(b, weight));
        vertexList[indexOf(b)].edgesList.add(new EdgeWeight(a, weight)); //grafo no dirigido
    }

    @Override
    public void addWeight(Object a, Object b, Object weight) throws GraphException, ListException {
        if(!containsVertex(a)||!containsVertex(b))
            throw new GraphException("Cannot add edge between vertexes ["+a+"] and ["+b+"]");
        EdgeWeight ew = (EdgeWeight)vertexList[indexOf(a)].edgesList.getNode(new EdgeWeight(b, null)).getData();
        ew.setWeight(weight); //setteo el peso que quiero agregar
        vertexList[indexOf(a)].edgesList.getNode(new EdgeWeight(b, null)).setData(ew);
        
        //grafo no dirigido
        ew = (EdgeWeight)vertexList[indexOf(b)].edgesList.getNode(new EdgeWeight(a, null)).getData();
        ew.setWeight(weight); //setteo el peso que quiero agregar
        vertexList[indexOf(b)].edgesList.getNode(new EdgeWeight(a, null)).setData(ew);
    }

    @Override
    public void removeVertex(Object element) throws GraphException, ListException {
        if(isEmpty()){
            throw new GraphException("Adjacency List Graph is Empty");
        }
        if(containsVertex(element)){
            for (int i = 0; i < counter; i++) {
                if(util.Utility.equals(vertexList[i].data, element)){
                    //se deben suprimir todas las aristas asociadas
                    for (int j = 0; j < counter; j++) {
                        if(containsEdge(vertexList[j].data, element)){
                            removeEdge(vertexList[j].data, element);
                        }
                    }
                    //se debe suprimir el vertice
                    for (int j = i; j < counter-1; j++) {
                        vertexList[j] = vertexList[j+1]; //se mueve los elementos una pos a la izq
                    }
                    counter--; //se disminuye el contador de vertices por el vertice suprimido
                }
            }
        }
    }

    @Override
    public void removeEdge(Object a, Object b) throws GraphException, ListException {
        if(!containsVertex(a)||!containsVertex(b))
            throw new GraphException("There's no some of vertexes");
        if(!vertexList[indexOf(a)].edgesList.isEmpty()){
            vertexList[indexOf(a)].edgesList.remove(new EdgeWeight(b, null));
        }
        //grafo no dirigido
        if(!vertexList[indexOf(b)].edgesList.isEmpty()){
            vertexList[indexOf(b)].edgesList.remove(new EdgeWeight(a, null));
        }
    }

    @Override
    public Vertex getVertexByIndex(int index) {
        if(index<0||index>=counter)
            return null;
        return vertexList[index];
    }
    
    @Override
    public String toString() {
        String result = "ADJACENCY LIST GRAPH CONTENT...";
        //vertexes info
        for (int i = 0; i < counter; i++) {
            result+="\nVertex in the position ["+i+"]: "+vertexList[i].data;
            if(!vertexList[i].edgesList.isEmpty()){
                result+="\n....EDGES: "+vertexList[i].edgesList.toString();
            }
        }
        return result;
    }
    
    /**
    *____________RECORRIDOS POR GRAFOS
    */
    
    /***
     * RECORRIDO EN PROFUNDIDAD
     * @return 
     * @throws domain.GraphException
     * @throws domain.stack.StackException
     * @throws domain.list.ListException
     */
    @Override
    public String dfs() throws GraphException, StackException, ListException {
        setVisited(false);//marca todos los vertices como no vistados
        String info =vertexList[0].data+", ";
         vertexList[0].setVisited(true); // lo marca
         stack.clear();
         stack.push(0); //lo apila
         while( !stack.isEmpty() ){
             // obtiene un vertice adyacente no visitado, 
             //el que esta en el tope de la pila
             int index = adjacentVertexNotVisited((int) stack.top());
             if(index==-1) // no lo encontro
                 stack.pop();
             else{
                 vertexList[index].setVisited(true); // lo marca
                 info+=vertexList[index].data+", ";
                 stack.push(index); //inserta la posicion
             }
         }
         return info;
	}//dfs

    /***
     * RECORRIDO POR AMPLITUD
     * @return 
     * @throws domain.GraphException
     * @throws domain.queue.QueueException
     * @throws domain.list.ListException
     */
    @Override
    public String bfs() throws GraphException, QueueException, ListException {
        setVisited(false);//marca todos los vertices como no visitados
         // inicia en el vertice 0
         String info =vertexList[0].data+", ";
         vertexList[0].setVisited(true); // lo marca
         queue.clear();
         queue.enQueue(0); // encola el elemento
         int index2;
         while(!queue.isEmpty()){
             int index1 = (int) queue.deQueue(); // remueve el vertice de la cola
             // hasta que no tenga vecinos sin visitar
             while((index2=adjacentVertexNotVisited(index1)) != -1 ){
                 // obtiene uno
                 vertexList[index2].setVisited(true); // lo marca
                 info+=vertexList[index2].data+", ";
                 queue.enQueue(index2); // lo encola
             } 
         }
    return info;
    }
    
    //setteamos el atributo visitado del vertice respectivo
    private void setVisited(boolean value) {
        for (int i = 0; i < counter; i++) {
            vertexList[i].setVisited(value); //value==true o false
        }//for
    }
    
    private int adjacentVertexNotVisited(int index) throws ListException {
        Object vertexData = vertexList[index].data;
        for(int i=0; i<counter; i++)
	    if(!vertexList[i].edgesList.isEmpty()
              &&vertexList[i].edgesList
                .contains(new EdgeWeight(vertexData, null)) 
                && !vertexList[i].isVisited())
	             return i;
	return -1;
    }
    
}
