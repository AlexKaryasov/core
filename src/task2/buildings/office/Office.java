/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task2.buildings.office;

import java.io.Serializable;
import task2.buildings.Space;
//import java.lang.Number;

/**
 *
 * @author Александр
 * @param <T>
 */
public class Office implements Space, Serializable{
    private int rooms;
    private double square;
    private final int DEFAULT_ROOMS = 1;
    private final double DEFAULT_SQUARE = 250.0;
    public Office(){
        this.rooms = DEFAULT_ROOMS;
        this.square = DEFAULT_SQUARE;
    }
    public Office(double square){
        this.rooms = DEFAULT_ROOMS;
        this.square = square;
    }
    public Office(double square, int rooms){
        this.square = square;
        this.rooms = rooms;
    }
    @Override
    public int getRooms(){
        return this.rooms;
    }
    @Override
    public void setRooms(int rooms){
        this.rooms = rooms;
    }
    @Override
    public double getSquare(){
        return this.square;
    }
    @Override
    public void setSquare(double square){
        this.square = square;
    }
    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer("");
        sb.append(this.getClass().getSimpleName()).append(" (").append(this.getRooms()).append(", ").append(this.getSquare()).append(") ");
        return sb.toString();
    }
    @Override
    public boolean equals(Object object){
        Office office = (Office) object;
        return (getClass() == object.getClass()) && (this.getRooms()== office.getRooms()) && (this.getSquare() == office.getSquare());
    }
    @Override
    public int hashCode(){
        return (int) new Byte((byte)this.getRooms()) ^ new Byte((byte)this.getSquare());
    }
    @Override
    public Object clone() throws CloneNotSupportedException{
        Space cloned = (Space)super.clone();
        return cloned;
    }
    @Override
    public int compareTo(Space o){
        if (this.getSquare() > o.getSquare()) return 1;
        if (this.getSquare() < o.getSquare()) return -1;
        return 0;
    }
}
