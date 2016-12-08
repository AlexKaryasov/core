/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task2.buildings.office;

import java.io.Serializable;
import task2.buildings.Floor;

/**
 *
 * @author Александр
 */
public class DoubleNode implements Serializable{
    private Floor element;
    //private DoubleNode head;
    private DoubleNode prev;
    private DoubleNode next;
    protected DoubleNode(){}
    protected DoubleNode(Floor element){
        this.element = element;
        this.next = this;
        this.prev = this;
    }
    protected Floor getElement(){
        return this.element;
    }
    protected void setElement(Floor element){
        this.element = element;
    }
    protected DoubleNode getNext(){
        return this.next;
    }
    protected void setNext(DoubleNode next){
        this.next = next;
    }
    protected DoubleNode getPrev(){
        return this.prev;
    }
    protected void setPrev(DoubleNode prev){
        this.prev = prev;
    }   
    @Override
    public Object clone() throws CloneNotSupportedException{
        DoubleNode cloned = (DoubleNode) super.clone();
        return cloned;
    }
}
