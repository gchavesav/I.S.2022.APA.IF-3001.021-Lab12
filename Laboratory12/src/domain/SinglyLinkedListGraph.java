/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;
import domain.list.*;
import domain.queue.LinkedQueue;
import domain.queue.QueueException;
import domain.stack.LinkedStack;
import domain.stack.StackException;

/**
 *
 * @author Profesor Gilberth Chaves A <gchavesav@ucr.ac.cr>
 */
public class SinglyLinkedListGraph implements Graph {
    private SinglyLinkedList vertexList; //definimos nuestra lista de vertices
    //para los recorridos dfs, bfs
    private LinkedStack stack;
    private LinkedQueue queue;
    
    //Constructor
    public SinglyLinkedListGraph(){
        this.vertexList = new SinglyLinkedList();
        this.stack = new LinkedStack();
        this.queue = new LinkedQueue();
    }

    @Override
    public int size() throws ListException {
        return vertexList.size();
    }

    @Override
    public void clear() {
        vertexList.clear();
    }

    @Override
    public boolean isEmpty() {
        return vertexList.isEmpty();
    }

    @Override
    public boolean containsVertex(Object element) throws GraphException, ListException {
        if(isEmpty()){
            throw new GraphException("Singly Linked List Graph is Empty");
        }
        for (int i=1; i<=vertexList.size(); i++) {
            Vertex vertex = (Vertex)vertexList.getNode(i).data;
            if(util.Utility.equals(vertex.data, element)){
                return true; //encontro el vertice
            }
        }
        return false;
    }

    @Override
    public boolean containsEdge(Object a, Object b) throws GraphException, ListException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addVertex(Object element) throws GraphException, ListException {
        if(vertexList.isEmpty()){
            vertexList.add(new Vertex(element));
        }else
            if(!vertexList.contains(element)){
                vertexList.add(new Vertex(element));
            }
    }

    @Override
    public void addEdge(Object a, Object b) throws GraphException, ListException {
        if(!containsVertex(a)||!containsVertex(b))
            throw new GraphException("Cannot add edge between vertexes ["+a+"] and ["+b+"]");
        addRemoveVertexEdgeWeight(a, b, null, "addEdge");
        addRemoveVertexEdgeWeight(b, a, null, "addEdge"); //grafo no dirigido
    }
    
    private void addRemoveVertexEdgeWeight(Object a, Object b, Object weight, String action) throws ListException {
        for (int i=1; i<=vertexList.size(); i++) {
            Vertex vertex = (Vertex)vertexList.getNode(i).data;
            if(util.Utility.equals(vertex.data, a)){
                switch(action){
                    case "addEdge":
                        vertex.edgesList.add(new EdgeWeight(b, weight));
                        break;
                    case "addWeight":
                        vertex.edgesList.getNode(new EdgeWeight(b, weight)).setData(new EdgeWeight(b, weight));
                        break;
                }
            }
        }
    }

    @Override
    public void addWeight(Object a, Object b, Object weight) throws GraphException, ListException {
        if(!containsVertex(a)||!containsVertex(b))
            throw new GraphException("Cannot add edge between vertexes ["+a+"] and ["+b+"]");
        addRemoveVertexEdgeWeight(a, b, weight, "addWeight");
        addRemoveVertexEdgeWeight(b, a, weight, "addWeight"); //grafo no dirigido
    }

    @Override
    public void removeVertex(Object element) throws GraphException, ListException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void removeEdge(Object a, Object b) throws GraphException, ListException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String dfs() throws GraphException, StackException, ListException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String bfs() throws GraphException, QueueException, ListException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Vertex getVertexByIndex(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public String toString(){
        String result = "SINGLY LINKED LIST GRAPH CONTENT...";
        try{
            for (int i = 1; i<=vertexList.size(); i++) {
                Vertex vertex = (Vertex) vertexList.getNode(i).data;
                result+="\nVertex at position ["+i+"]: "+vertex.data;
                if(!vertex.edgesList.isEmpty()){
                    result+="\n...EDGES AND WEIGHTS: "+vertex.edgesList+"\n";
                }
            }
        }catch(ListException ex){
            System.out.println(ex.getMessage());
        }
        return result;
    }

}
