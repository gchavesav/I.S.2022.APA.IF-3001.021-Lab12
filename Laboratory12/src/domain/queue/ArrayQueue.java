/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain.queue;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Profesor Gilberth Chaves A <gchavesav@ucr.ac.cr>
 */
public class ArrayQueue implements Queue {
    private int n; //total de elementos
    private int front, rear; //anterior y posterior
    private Object queue[];

    //Constructor
    public ArrayQueue(int n){
        if(n<=0) System.exit(-1); //error
        this.n = n;
        this.queue = new Object[n];
        this.rear = n-1;
        this.front = rear;
    }
    
    @Override
    public int size() {
        return rear-front;
    }

    @Override
    public void clear() {
        this.queue = new Object[n];
        this.rear = n-1;
        this.front = rear;
    }

    @Override
    public boolean isEmpty() {
        return front==rear;
    }

    @Override
    public int indexOf(Object element) throws QueueException {
        if(isEmpty())
            throw new QueueException("Array Queue is Empty");
        ArrayQueue aux = new ArrayQueue(size());
        int i = 0; //index
        int j = -1; //si es -1 no existe
        try {
            while(!isEmpty()){
                if(util.Utility.equals(front(), element)){
                    j = i;
                }
                aux.enQueue(deQueue());
                i++;
            }
            //al final dejamos la cola en su estado original
            while(!aux.isEmpty()){
                enQueue(aux.deQueue());
            }
                
        } catch (QueueException ex) {
            Logger.getLogger(ArrayQueue.class.getName()).log(Level.SEVERE, null, ex);
        }
        return j; //retorna -1 si el elemento no fue encolado
    }

    @Override
    public void enQueue(Object element) throws QueueException {
        if(size()==this.queue.length){
            throw new QueueException("Array Queue is full");
        }
        //la primera, cuando la cola esta vacia, no entra al for
        for (int i = front; i < rear; i++) {
            this.queue[i] = this.queue[i+1];
            
        }
        this.queue[rear] = element;
        front--; //siempre queda en una casilla vacia
    }

    @Override
    public Object deQueue() throws QueueException {
        if(isEmpty())
            throw new QueueException("Array Queue is Empty");
        return this.queue[++front];
    }

    @Override
    public boolean contains(Object element) throws QueueException {
        if(isEmpty())
            throw new QueueException("Array Queue is Empty");
        ArrayQueue aux = new ArrayQueue(size());
        boolean finded = false; //encontrado
        try {
            while(!isEmpty()){
                if(util.Utility.equals(front(), element)){
                    finded = true;
                }
                aux.enQueue(deQueue());
            }
            //al final dejamos la cola en su estado original
            while(!aux.isEmpty()){
                enQueue(aux.deQueue());
            }
                
        } catch (QueueException ex) {
            Logger.getLogger(ArrayQueue.class.getName()).log(Level.SEVERE, null, ex);
        }
        return finded;
    }

    @Override
    public Object peek() throws QueueException {
        return this.queue[front+1];
    }

    @Override
    public Object front() throws QueueException {
        return this.queue[front+1];
    }

    @Override
    public String toString() {
        String result = "Array Queue Content: \n";
        ArrayQueue aux = new ArrayQueue(size());
        try {
            while(!isEmpty()){
                    result+=front()+"\n";
                    aux.enQueue(deQueue());
            }
            //al final dejamos la cola en su estado original
            while(!aux.isEmpty()){
                enQueue(aux.deQueue());
            }
                
        } catch (QueueException ex) {
            Logger.getLogger(ArrayQueue.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
}
