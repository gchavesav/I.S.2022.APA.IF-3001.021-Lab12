/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author Profesor Gilberth Chaves A <gchavesav@ucr.ac.cr>
 */
public class EdgeWeight {
    private Object edge; //arista
    private Object weight; //peso

    public EdgeWeight(Object edge, Object weight) {
        this.edge = edge;
        this.weight = weight;
    }

    public Object getEdge() {
        return edge;
    }

    public void setEdge(Object edge) {
        this.edge = edge;
    }

    public Object getWeight() {
        return weight;
    }

    public void setWeight(Object weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        //return "EdgeWeight{" + "edge=" + edge + ", weight=" + weight + '}';
        if(weight==null) return "Edge="+edge;
        else return "Edge="+edge+". Weight="+weight;
    }
    
    
    
}
