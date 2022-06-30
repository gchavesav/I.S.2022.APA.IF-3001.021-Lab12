/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package domain;

import domain.list.ListException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.testng.annotations.Test;

/**
 *
 * @author Profesor Gilberth Chaves A <gchavesav@ucr.ac.cr>
 */
public class SinglyLinkedListGraphNGTest {

    @Test
    public void test() {
       try {
           SinglyLinkedListGraph graph = new SinglyLinkedListGraph();
            for (int i = 1; i <= 10; i++) {
               graph.addVertex(i);
            }
            graph.addEdge(1, 3); 
            //graph.addEdgeWeight(1, 3, util.Utility.random(10, 50)); 
            //graph.addWeight(1, 3, util.Utility.random(10, 50));
            
            graph.addEdge(2, 4); 
            //graph.addEdgeWeight(2, 4, util.Utility.random(10, 50)); 
            //graph.addWeight(2, 4, util.Utility.random(10, 50));
            
            graph.addEdge(3, 6); 
            //graph.addEdgeWeight(3, 6, util.Utility.random(10, 50)); 
            //graph.addWeight(3, 6, util.Utility.random(10, 50));
            
            graph.addEdge(4, 8); 
            //graph.addEdgeWeight(4, 8, util.Utility.random(10, 50)); 
            //graph.addWeight(4, 8, util.Utility.random(10, 50));
            
            graph.addEdge(5, 10); 
            //graph.addEdgeWeight(5, 10, util.Utility.random(10, 50)); 
            //graph.addWeight(5, 10, util.Utility.random(10, 50));
            
            System.out.println(graph.toString());
            
            
            
        } catch (GraphException | ListException ex) {
               Logger.getLogger(SinglyLinkedListGraphNGTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
