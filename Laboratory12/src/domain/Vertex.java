/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import domain.list.SinglyLinkedList;

/**
 *
 * @author Profesor Gilberth Chaves A <gchavesav@ucr.ac.cr>
 */
public class Vertex {
    public Object data;
    public SinglyLinkedList edgesList; //lista de aristas para cada vertice
    private boolean visited; //para los recorridos DFS, BFS
    
    //Constructor
    public Vertex(Object data) {
        this.data = data;
        this.visited = false;
        this.edgesList = new SinglyLinkedList();
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    
    
    
}
