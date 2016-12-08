/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task2.buildings.office;

import java.io.Serializable;
import task2.buildings.Space;

/**
 *
 * @author Александр
 */
public class Node implements Serializable{
    //static class Node {
    private Space element;
    private Node next;
    //private int size;
    public Space getElement(){
        return element;
    }
    public void setElement(Space e){
        element = e;
    }
    public Node getNext(){
        return next;
    }
    public void setNext(Node n){
        next = n;
    }
    @Override
    public Object clone() throws CloneNotSupportedException{
        Node cloned = (Node) super.clone();
        return cloned;
    }
    protected Node(){}
    protected Node(Space e){
        this.element = e;
        this.next = this;
      //  this.size = 1;
    }
    /*protected int getSize(){
        return this.size;
    }*/
    /*protected void setSize(int number){
        this.size = number;
    }*/
    
}

